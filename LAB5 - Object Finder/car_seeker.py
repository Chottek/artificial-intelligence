import cv2


cascade_src = 'cascade_car.xml'
car_cascade = cv2.CascadeClassifier(cascade_src)

cap = cv2.VideoCapture("cars/Camera_surveillance.mp4")

while True:
    ret, img = cap.read()

    carsNum = 0

    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    cars = car_cascade.detectMultiScale(gray, 1.1, 3)

    for (x, y, w, h) in cars:
        carsNum += 1
        cv2.rectangle(img, (x, y), (x + w, y + h), (0, 0, 255), 2)

    cv2.putText(img, "Cars: {}".format(carsNum), (10, 15), cv2.FONT_ITALIC, 0.7, (0, 0, 0))
    cv2.imshow('CarSeeker', img)

    if cv2.waitKey(1) == 27:
        break

cv2.destroyAllWindows()

