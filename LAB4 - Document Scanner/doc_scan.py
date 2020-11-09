# Wyobraźmy sobie, że mamy do zeskanowania dokument. Chcielibyśmy, aby zawsze był on poprawnie zorientowany
# (góra u góry, dół na dole, a nie do góry nogami).
# Nie mamy skanera, więc musimy skorzystać z aparatu w telefonie lub w laptopie.
# Zasada działania: Użytkownik kładzie kartkę do "zeskanowania" na ciemnym stole,
# a następnie kładzie na kartce marker (na przykład coś o kolorze różowym)
# w prawym dolnym rogu tej kartki. Kieruje kamerę na tą kartkę. Program wyłapuje prostokątną kartkę papieru,
# sprawdza czy jest na niej marker, jeśli tak, to robi zdjęcie, poprawia perspektywę tak aby kartka była prostokątna,
# obraca jeśli trzeba tak aby marker był na dole i zapisuje na dysku. Może zapisywać na przykład serię tak zeskanowanych
# kartek w celu umożliwienia użytkownikowi wyboru najlepszego skanu.

# protip: cv2.rotate(image,image,cv2.rotate_90_clockwise);

from datetime import datetime

import cv2
import numpy as np
from imutils.perspective import order_points


def transform(image, pts):
    # obtain a consistent order of the points and unpack them
    # individually
    rect = order_points(pts)
    (tl, tr, br, bl) = rect

    # compute the width of the new image, which will be the
    # maximum distance between bottom-right and bottom-left
    # x-coordiates or the top-right and top-left x-coordinates
    widthA = np.sqrt(((br[0] - bl[0]) ** 2) + ((br[1] - bl[1]) ** 2))
    widthB = np.sqrt(((tr[0] - tl[0]) ** 2) + ((tr[1] - tl[1]) ** 2))
    maxWidth = max(int(widthA), int(widthB))

    # compute the height of the new image, which will be the
    # maximum distance between the top-right and bottom-right
    # y-coordinates or the top-left and bottom-left y-coordinates
    heightA = np.sqrt(((tr[0] - br[0]) ** 2) + ((tr[1] - br[1]) ** 2))
    heightB = np.sqrt(((tl[0] - bl[0]) ** 2) + ((tl[1] - bl[1]) ** 2))
    maxHeight = max(int(heightA), int(heightB))

    # now that we have the dimensions of the new image, construct
    # the set of destination points to obtain a "birds eye view",
    # (i.e. top-down view) of the image, again specifying points
    # in the top-left, top-right, bottom-right, and bottom-left
    # order
    dst = np.array([
        [0, 0],
        [maxWidth - 1, 0],
        [maxWidth - 1, maxHeight - 1],
        [0, maxHeight - 1]], dtype="float32")

    # compute the perspective transform matrix and then apply it
    M = cv2.getPerspectiveTransform(rect, dst)
    warped = cv2.warpPerspective(image, M, (maxWidth, maxHeight))

    # return the warped image
    return warped


url = "http://192.168.0.73:8080/video"

pink_lower = np.array([136, 90, 111], np.uint8)
pink_upper = np.array([180, 255, 255], np.uint8)

video = cv2.VideoCapture(url)

while True:

    ret, frame = video.read()

    frame = cv2.resize(frame, (680, 400))
    outline = frame

    ratio = frame.shape[0] / 500.0

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    gray = cv2.GaussianBlur(gray, (5, 5), 0)
    edged = cv2.Canny(gray, 75, 200)

    contours, hierarchy = cv2.findContours(edged, cv2.RETR_LIST, cv2.CHAIN_APPROX_NONE)

    scr = None

    for c in contours:
        area = cv2.contourArea(c)

        if area > 800:
            peri = cv2.arcLength(c, True)
            approx = cv2.approxPolyDP(c, 0.02 * peri, True)

            if len(approx) == 4:
                scr = approx
                x, y, w, h = cv2.boundingRect(c)
                break
            else:
                continue

    if scr is not None:
        cv2.drawContours(outline, [scr], -1, (0, 0, 255), 5)

    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    pink = cv2.inRange(hsv, pink_lower, pink_upper)
    pink = cv2.erode(pink, None, iterations=2)
    pink = cv2.dilate(pink, None, iterations=2)

    contours, hierarchy = cv2.findContours(pink, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

    a = None

    for contour in contours:
        area = cv2.contourArea(contour)

        if area > 300:
            p = cv2.arcLength(contour, True)
            a = cv2.approxPolyDP(contour, 0.02 * p, True)

    if scr is not None and a is not None:
        cv2.drawContours(outline, [a], -1, (0, 0, 255), 5)
        img_name = "scrshot_{}.png".format(datetime.today().strftime('%Y%m%d%H%M%S'))
        warped = transform(frame, scr.reshape(4, 2) * ratio)
        cv2.imwrite(img_name, warped)
        # im = cv2.imread("scrshot_{}.png".format(counter))

    cv2.imshow("ContourSeeker", edged)
    cv2.imshow("Outline", outline)
    cv2.imshow("RealView", frame)

    key = cv2.waitKey(1)
    if key == 27:
        break

video.release()
cv2.destroyAllWindows()
