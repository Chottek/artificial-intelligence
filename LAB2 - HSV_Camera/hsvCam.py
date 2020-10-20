import cv2
import sys
import numpy as np


def nothing(x):
    pass


width = 320
height = 200

print('Number of arguments: ', len(sys.argv))

if (len(sys.argv)) == 3:
    width = sys.argv[1]
    height = sys.argv[2]
    print('Setting width to ' + width + ' and height to ' + height)
else:
    print('Dimensions set by default to 320x200')

vid = cv2.VideoCapture(0)

counter = 0

# Tworzenie okienka z suwaczkami
cv2.namedWindow("HSVGetter")
cv2.createTrackbar("L - H", "HSVGetter", 0, 179, nothing)
cv2.createTrackbar("L - S", "HSVGetter", 0, 255, nothing)
cv2.createTrackbar("L - V", "HSVGetter", 0, 255, nothing)
cv2.createTrackbar("U - H", "HSVGetter", 179, 179, nothing)
cv2.createTrackbar("U - S", "HSVGetter", 255, 255, nothing)
cv2.createTrackbar("U - V", "HSVGetter", 255, 255, nothing)

while True:

    ret, frame = vid.read()
    flip = cv2.flip(frame, 1)
    blurred = cv2.GaussianBlur(flip, (5, 5), 1)
    resized = cv2.resize(blurred, (int(width), int(height)))
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    l_h = cv2.getTrackbarPos("L - H", "HSVGetter")
    l_s = cv2.getTrackbarPos("L - S", "HSVGetter")
    l_v = cv2.getTrackbarPos("L - V", "HSVGetter")
    u_h = cv2.getTrackbarPos("U - H", "HSVGetter")
    u_s = cv2.getTrackbarPos("U - S", "HSVGetter")
    u_v = cv2.getTrackbarPos("U - V", "HSVGetter")

    lower_range = np.array([l_h, l_s, l_v])
    upper_range = np.array([u_h, u_s, u_v])

    mask = cv2.inRange(hsv, lower_range, upper_range)

    # You can also visualize the real part of the target color (Optional)
    res = cv2.bitwise_and(frame, frame, mask)

    # Converting the binary mask to 3 channel image, this is just so
    # we can stack it with the others
    mask_2 = cv2.cvtColor(mask, cv2.COLOR_GRAY2BGR)

    # stack the mask, orginal frame and the filtered result
    stacked = np.hstack((mask_2, hsv, res))

    cv2.imshow('Suwaczki', cv2.resize(stacked, None, fx=0.9, fy=0.9))

    key = cv2.waitKey(1)

    if key == 27:
        break

    if key == ord('x'):
        img_name = "scrshot_{}.png".format(counter)
        cv2.imwrite(img_name, hsv)
        counter += 1

vid.release()

cv2.destroyAllWindows()
