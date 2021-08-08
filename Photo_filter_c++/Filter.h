#include "Image.h"
#include <complex.h>
#include <cmath>

namespace imaging{

class Filter{
	
	virtual Image operator << (const Image & image)=0;
};









class FilterGamma:public Filter{
	public :
	float y=0.6;
	
	
	
	
	Image operator << (const Image & img1){
		
		 Image img2(img1.getWidth(),img1.getHeight());
	     unsigned int i,j,x_p,y_p,z_p;
		 Vec3<float> t;
		 int cnt=0;
		 for(i=0;i<img1.getWidth();i++){//every color of every pixel changes 
			 for(j=0;j<img1.getHeight();j++){
				 t=img1.getElement(i,j);
				 x_p=powf(t.x,y);
				 y_p=powf(t.y,y);
				 z_p=powf(t.z,y);
				 
				 
				 Vec3<float> put(x_p,y_p,z_p);
				
				 img2.addTobuf(put);
				 cnt++;
			 }
	   }
	   
		cout<<img2.getWidth();
		
		return img2;
		
	}
	
	FilterGamma(float y){
		this->y=y;
		
	}
	
	
	
	
	
};



class FilterLinear:public Filter{
	
	public :
	Vec3<float> a,c;
	
	
	Image operator << (const Image & img1){
		
		 Image img2(img1.getWidth(),img1.getHeight());
	     unsigned int i,j;
		 
		 Vec3<float> t;
		 for(i=0;i<img1.getWidth();i++){//every color of every pixel changes 
			 for(j=0;j<img1.getHeight();j++){
				 t=img1.getElement(i,j);
				 t=t*a;
				 t=t+c;
				 if (t.x > 1.0) t.x = 1.0;	//here we check if the color goes out of bounds
				 if (t.x < 0.0) t.x = 0.0;
                 if (t.y > 1.0) t.y = 1.0;
				 if (t.y < 0.0) t.y = 0.0;
                 if (t.z > 1.0) t.z = 1.0;
				 if (t.z < 0.0) t.z = 0.0;

				 
				 img2.addTobuf(t);
				
				 
				 
			 }
	   }
		
		return img2;
		
	}
	
	FilterLinear(Vec3<float> & a,Vec3<float> & c){
		this->a=a;
		this->c=c;
	}
};


}




