import sys
import cv2


# Napisz program, który korzystając z OpenCV wykona następujące
# rzeczy (w pętli):
#
# Pobierze z kamery klatkę obrazu

# Rozmyje obraz za pomocą rozmycia Gaussa(parametry wybierz sobie takie, jak Tobie wydadzą się odpowiednie)

# Przeskaluje ją do rozmiaru takiego, jak podany w argumencie wywołania Twojego programu(czyli będzie można
# z linii komend powiedzieć jak wielki ma być obrazek).Jeśli nie podano argumentów, niech będzie to 320 x200

# Przekształci przestrzeń barw do formatu HSV(niech to pojawia się w okienku)

# Dokona operacji inRange dla zadanych zakresów - zakresy niech będą podane za pomocą suwaków.(to będzie drugie okienko)
# Wartości zakresów niech pojawiają się w okienku z obrazem w wersji HSV

# Jeżeli użytkownik naciśnie klawisz < x > to zostanie zrobione "zdjęcie" wybranego przez użytkownika fragmentu
# obrazu - wybieramy ROI.To zdjęcie niech zostanie zapisane na dysk pod nazwą taką, jak sobie wymyślisz.
# Podpowiem, że można skorzystać z imwrite


max_value = 255
max_value_H = 360 // 2
low_H = 0
low_S = 0
low_V = max_value // 2
high_H = max_value_H
high_S = max_value
high_V = max_value

WINDOW_NAME = 'HSVGetter'
DEFAULT_WIDTH = 320
DEFAULT_HEIGHT = 200
counter = 0

def on_low_H_thresh_trackbar(val):
    global low_H
    global high_H
    low_H = val
    low_H = min(high_H - 1, low_H)
    cv2.setTrackbarPos("L Hue", WINDOW_NAME, low_H)


def on_high_H_thresh_trackbar(val):
    global low_H
    global high_H
    high_H = val
    high_H = max(high_H, low_H + 1)
    cv2.setTrackbarPos("H Hue", WINDOW_NAME, high_H)


def on_low_S_thresh_trackbar(val):
    global low_S
    global high_S
    low_S = val
    low_S = min(high_S - 1, low_S)
    cv2.setTrackbarPos("L Sat", WINDOW_NAME, low_S)


def on_high_S_thresh_trackbar(val):
    global low_S
    global high_S
    high_S = val
    high_S = max(high_S, low_S + 1)
    cv2.setTrackbarPos("H Sat", WINDOW_NAME, high_S)


def on_low_V_thresh_trackbar(val):
    global low_V
    global high_V
    low_V = val
    low_V = min(high_V - 1, low_V)
    cv2.setTrackbarPos("L Val", WINDOW_NAME, low_V)


def on_high_V_thresh_trackbar(val):
    global low_V
    global high_V
    high_V = val
    high_V = max(high_V, low_V + 1)
    cv2.setTrackbarPos("H Val", WINDOW_NAME, high_V)


cv2.namedWindow(WINDOW_NAME)
cv2.createTrackbar("L Hue", WINDOW_NAME, low_H, max_value_H, on_low_H_thresh_trackbar)
cv2.createTrackbar("H Hue", WINDOW_NAME, high_H, max_value_H, on_high_H_thresh_trackbar)
cv2.createTrackbar("L Sat", WINDOW_NAME, low_S, max_value, on_low_S_thresh_trackbar)
cv2.createTrackbar("H Sat", WINDOW_NAME, high_S, max_value, on_high_S_thresh_trackbar)
cv2.createTrackbar("L Val", WINDOW_NAME, low_V, max_value, on_low_V_thresh_trackbar)
cv2.createTrackbar("H Val", WINDOW_NAME, high_V, max_value, on_high_V_thresh_trackbar)

vid = cv2.VideoCapture(0)

while True:
    ret, frame = vid.read()

    frame_HSV = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    frame_threshold = cv2.inRange(frame_HSV, (low_H, low_S, low_V), (high_H, high_S, high_V))
    flipped = cv2.flip(frame, 1)
    hsv = cv2.cvtColor(flipped, cv2.COLOR_BGR2HSV)
    blur = cv2.GaussianBlur(hsv, (5, 5), cv2.BORDER_DEFAULT)

    range1 = "Low: {}, {}, {}".format(low_H, low_S, low_V)
    range2 = "High: {}, {}, {}".format(high_H, high_S, high_V)
    image = cv2.putText(blur, range1, (10, 20), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255))
    image = cv2.putText(blur, range2, (10, 40), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255))

    if (len(sys.argv)) == 3:
        window = cv2.resize(blur, (int(sys.argv[1]), int(sys.argv[2])))
    else:
        window = cv2.resize(blur, (DEFAULT_WIDTH, DEFAULT_HEIGHT))

    # Display the final image:
    cv2.imshow("Current", window)
    cv2.imshow(WINDOW_NAME, frame_threshold)

    key = cv2.waitKey(1)
    if key == 27:
        break
    if key == ord('x'):
        img_name = "scrshot_{}.png".format(counter)
        cv2.imwrite(img_name, hsv)
        counter += 1

vid.release()
cv2.destroyAllWindows()
