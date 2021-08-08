//------------------------------------------------------------
//
// C++ course assignment code 
//
// G. Papaioannou, 2017 - 2018
//
//


#include "Color.h"
#include "Vec3.h"
#include "Array.h"
#include "ppm.h"
using namespace math;
using namespace std;
#include <string>

/*! The imaging namespace contains every class or function associated with the image storage, compression and manipulation. 
 */ 

namespace imaging
{
	
	class Image:public Array <Vec3<float>>{
	                                          
	
	public:
	
		Image(){
			Array<Vec3<float>>();
		}											     	
		
		
		Image(unsigned int width, unsigned int height){
			this->width=width;
			this->height=height;
		}
		
		
		Image(unsigned int width, unsigned int height,const vector<Vec3<float>> * data_ptr){
			Array<Vec3<float>>(width, height, data_ptr);
		}
		
	
		Image(const Image &src){
			
			width = src.width;
			height = src.height;
			buffer.resize(width*height);
			for (int i = 0; i < buffer.size(); i++) {
				buffer[i] = src.buffer[i];
			}
		
		}
		
		
		
		
		
		bool load(const std::string & filename, const std::string & format){
			
			int a= filename.length();
			string help="";
			
			
			help+=filename.at(a-3);
			help+=filename.at(a-2);
			help+=filename.at(a-1);
			
			
			//help contains the last 3 chars of the string 
			if(!help.compare(format)){//if the file is ppm file
				int w=0,h=0;
				const char*file=filename.c_str();
				float *p=ReadPPM(file,&w,&h);
				
				if(p==nullptr)
					return false;
				int i=0,j=0;
				float x,y,z;
				
				while(j<w*h){
					x=(p[i]+128)/255;//the number should be between(0,1)
					i++;
					y=(p[i]+128)/255;
					i++;
					z=(p[i]+128)/255;
					i++;
					buffer.push_back(Vec3<float>(x,y,z));
					j++;
				
				}
			
				width=w;//the reason we use pointers in Read for width,height
				height=h;

				return true;		
				
			}else{
				cout<<"File not supported!";
				return false;
			}
			
			
		}

		bool save(const std::string & filename, const std::string & format){
			int a= filename.length();
			string help="";
			
			
			help+=filename.at(a-3);
			help+=filename.at(a-2);
			help+=filename.at(a-1);
			
			
			if(!help.compare(format)){
				const char*file=filename.c_str();
				float * p=new float[3*width*height];
				int i=0;
				unsigned int j=0;
				while(j<width*height*3){
					p[j]=(buffer[i].x*255)-128;
					j++;
					p[j]=(buffer[i].y*255)-128;
					j++;
					p[j]=(buffer[i].z*255)-128;
					j++;
					i++;
				}
				
					
				
				if(WritePPM(p,width,height,file)){
						
						return true;
				}else{
					cout<<"File not supported!";
					return false;
					
				}
					delete[] p;
				}
			return false;
				
			
		}

	};

} //namespace imaging
