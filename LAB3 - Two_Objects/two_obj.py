import cv2

greenLower = (35, 21, 62)
greenUpper = (90, 255, 255)

video = cv2.VideoCapture(0)

while True:

    firstBiggest = 0
    secondBiggest = 0
    firstContour = None
    secondContour = None

    ret, frame = video.read()

    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    greenMask = cv2.inRange(hsv, greenLower, greenUpper)
    greenMask = cv2.erode(greenMask, None, iterations=2)
    greenMask = cv2.dilate(greenMask, None, iterations=2)

    contours, hierarchy = cv2.findContours(greenMask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

    for pic, contour in enumerate(contours):
        area = cv2.contourArea(contour)

        if area > 800:
            if area > firstBiggest:
                firstBiggest = area
                firstContour = contour

            elif firstBiggest > area > secondBiggest:
                secondBiggest = area
                secondContour = contour

    x, y, w, h = cv2.boundingRect(firstContour)
    imageFrame = cv2.rectangle(frame, (x, y), ((x + w), (y + h)), (0, 255, 0), 2)
    cv2.putText(imageFrame, "Match", (x, (y - 5)), cv2.FONT_ITALIC, 0.5, (0, 255, 0))

    x1, y1, w1, h1 = cv2.boundingRect(secondContour)
    imageFrame1 = cv2.rectangle(frame, (x1, y1), ((x1 + w1), (y1 + h1)), (0, 255, 0), 2)
    cv2.putText(imageFrame1, "Match", (x1, (y1 - 5)), cv2.FONT_ITALIC, 0.5, (0, 255, 0))

    if y == y1 or y + h == y1 + h1:
        cv2.line(imageFrame, (x + int(w / 2), y + int(h / 2)), (x1 + int(w1 / 2), y1 + int(h1 / 2)), (0, 0, 255), thickness=3, lineType=5)

    cv2.imshow("GreenDetector", frame)

    key = cv2.waitKey(1)
    if key == 27:
        break

video.release()
cv2.destroyAllWindows()
