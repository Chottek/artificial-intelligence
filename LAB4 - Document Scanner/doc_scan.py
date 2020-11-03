import cv2

url = "http://192.168.0.73:8080/video"

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
                break
            else:
                continue

    if scr is not None:
        cv2.drawContours(frame, [scr], -1, (0, 0, 255), 2)

    cv2.imshow("ContourSeeker", edged)
    cv2.imshow("RealView", frame)

    key = cv2.waitKey(1)
    if key == 27:
        break

video.release()
cv2.destroyAllWindows()
