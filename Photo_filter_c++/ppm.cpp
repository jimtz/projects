#include "ppm.h"
#include <iostream>
#include <fstream>
#include <string>
using namespace std;
namespace imaging{
	
	
	
	float * ReadPPM( const char* filename, int *  w, int * h ){
	ifstream d;
	d.open(filename,ofstream::binary);
		
	if(!d.is_open()){
	   cerr<<"Cannot open file";
	   return nullptr;
	   }
	else{  //first i count the lines to make sure that there is text inside
		string line;
		int lineCnt = 0;
	    while(getline(d,line)) 
		    lineCnt++;
	   
	   if (lineCnt == 0) {
		cout << "No image found!" << endl;
		return nullptr;
	   }
	   

	
	   d.clear();
	   d.seekg(0);//return to the top

	

	line="";
	line.push_back(d.get());
	line.push_back(d.get());
	
	//i create the string line and with the method push_back i add the next char i read to the string
      
	
	   if ( line.compare("P6") != 0 ) {
		   cout << "This is not a supported Image file !" << endl;
		   return nullptr;
	   }else{

	// Now since this is the file we expected lets put the Names in our Data Structure
	
	   int timi;
	   d>>*w>>*h>>timi;
	   if((*w==0)||(*h==0)||(timi==0)){
		   cout<<"Error ,height ,weight or timi is missing";
	   }else if(timi>255){
		   cout<<"Not supported image value";
	   }else{
	    
	  char * help=new char[(*w)*(*h)*3];//new char array
	  float * p=new float[(*w)*(*h)*3];
		  d.read(help,3*(*w)*(*h));//takes the values
	  for(int i=0;i<(*w)*(*h)*3;i++)
		  p[i]=(char)help[i];//and then casts them to the float array p
	   delete[] help; 
	   return p;
	
	   }
	
	   }	
	}
			return nullptr;
		
		
	}

	bool WritePPM(const float * data, int w, int h, const char* filename){
		ofstream d;
		if(data==nullptr){
			cerr<<"No data found";
			return false;
			
			}
		else{
			
			ofstream d;
			d.open(filename,ofstream::binary);
			if(!d.is_open()){
				cout<<"Error opening new file";
				return false;
			}
			
			
		d<<"P6"<<endl<<w<<endl<<h<<endl<<255;
			
		char *help=new char[w*h*3];
		for(int i=0;i<w*h*3;i++)
			help[i]=(char)data[i];
		//put the float values to char array
		d.write(help,w*h*3);
		delete[] help;
		return true;
		
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}