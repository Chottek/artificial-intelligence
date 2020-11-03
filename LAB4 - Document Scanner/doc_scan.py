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

import cv2
import numpy as np

url = "http://192.168.0.73:8080/video"

pink_lower = np.array([136, 90, 111], np.uint8)
pink_upper = np.array([180, 255, 255], np.uint8)

video = cv2.VideoCapture(url)

while True:

    ret, frame = video.read()

    frame = cv2.resize(frame, (680, 400))

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    gray = cv2.GaussianBlur(gray, (5, 5), 0)
    edged = cv2.Canny(gray, 75, 200)

    contours, hierarchy = cv2.findContours(edged, cv2.RETR_LIST, cv2.CHAIN_APPROX_NONE)

    for c in contours:
        area = cv2.contourArea(c)

        scr = None

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
        cv2.drawContours(frame, [scr], -1, (0, 0, 255), 5)
        cv2.putText(frame, "Match", (int((x + w) / 2), int((y + h) / 2)), cv2.FONT_ITALIC, 1, (0, 0, 255))

    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    pink = cv2.inRange(hsv, pink_lower, pink_upper)
    pink = cv2.erode(pink, None, iterations=2)
    pink = cv2.dilate(pink, None, iterations=2)

    cv2.imshow("ContourSeeker", edged)
    cv2.imshow("RealView", frame)
    cv2.imshow("NOR", pink)

    key = cv2.waitKey(1)
    if key == 27:
        break

video.release()
cv2.destroyAllWindows()
