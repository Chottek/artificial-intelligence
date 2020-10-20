import cv2

vid = cv2.VideoCapture(0)

while True:

    ret, frame = vid.read()

    # Display the resulting frame
    flip = cv2.flip(frame, 1)
    cv2.imshow('frame', flip)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

vid.release()
cv2.destroyAllWindows()