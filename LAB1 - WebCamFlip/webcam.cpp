#include <opencv4/opencv2/opencv.hpp>
#include <opencv4/opencv2/highgui/highgui_c.h>
#include <iostream>

int main(int argc, char** argv){
    bool capturing = true;
   cv::VideoCapture cap(0);
    if (!cap.isOpened()){
    std::cerr << "Error opening frame source" << std::endl;
    return -1;
    }
    std::cout << "Video size: " << cap.get(cv::CAP_PROP_FRAME_WIDTH) << "x" << cap.get(cv::CAP_PROP_FRAME_HEIGHT) << std::endl;
    do{
        cv::Mat frame;
        if(cap.read(frame)){
	    cv::flip(frame,frame,1); //flipping webcam horrizontaly        		
            cv::imshow( "Not-yet smart window", frame);
        }else{
            // stream finished
            capturing = false;
        }
        //Czekaj na klawisz, sprawdź czy to jest 'esc'
        if((cv::waitKey(1000.0 / 60.0)& 0x0ff) == 27){
 		capturing = false;
	}
    }while(capturing);
    return 0;
}
