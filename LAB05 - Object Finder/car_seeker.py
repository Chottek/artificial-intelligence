import cv2

cascade_src = 'cascade_car.xml'
video_src = 'cars/cam_sur.mp4'

car_cascade = cv2.CascadeClassifier(cascade_src)

vid = cv2.VideoCapture(video_src)

minCW = 20
minCH = 20
approx = 3
line_height = 250
matches = []


def carCenter(x, y, w, h):
    return x + int(w / 2), y + int(h / 2)


carCount = 0

while True:
    ret, frame = vid.read()

    frame = cv2.resize(frame, (600, 400))

    cv2.line(frame, (0, line_height), (3000, line_height), (0, 0, 255), 2)

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    cars = car_cascade.detectMultiScale(gray, 1.1, 3)

    for (x, y, w, h) in cars:
        if not [(w >= minCW) and (h >= minCH)]:
            continue

        cv2.rectangle(frame, (x - approx, y - approx), (x + w + approx, y + h + approx), (0, 0, 255), 2)
        matches.append(carCenter(x, y, w, h))

    for (x, y) in matches:
        if (line_height + approx) > y > (line_height - approx):
            carCount += 1
            cv2.line(frame, (0, line_height), (3000, line_height), (255, 255, 0), 2)
            matches.remove((x, y))
            continue

    cv2.putText(frame, "Cars: {}".format(carCount), (10, 20), cv2.FONT_ITALIC, 0.6, (0, 0, 0), 1)
    cv2.imshow('BigBrotherWatches', frame)

    if cv2.waitKey(1) == 27:
        break

vid.release()
cv2.destroyAllWindows()
