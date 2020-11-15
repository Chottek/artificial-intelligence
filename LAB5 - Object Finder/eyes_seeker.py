import cv2

cascade_src = 'cascade_eye.xml'

cap = cv2.VideoCapture(0)

eye_cascade = cv2.CascadeClassifier(cascade_src)

nooyos = 0

while True:
    ret, frame = cap.read()

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    los_oyos = eye_cascade.detectMultiScale(gray, 1.01, 1)

    nooyos = 0

    for (x, y, w, h) in los_oyos:
        nooyos += 1
        cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 3)
        if nooyos > 1:
            break

    cv2.putText(frame, "Eyes: {}".format(nooyos), (10, 10), cv2.FONT_ITALIC, 0.5, (0, 255, 0))

    cv2.imshow('EyeShow', frame)

    k = cv2.waitKey(1)

    if k == 27:
        break


cap.release()
cv2.destroyAllWindows()
