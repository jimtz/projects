#include <iostream>
using namespace std;
#include <string>
#include "Filter.h"



using namespace imaging;
using namespace math;

	
	


	       


		   
int main(int argc,char* argv[]){

	
	
	string ht;
	Image s(0,0);
	string st(argv[argc-1]);
	
	if(!s.load(st,"ppm"))
		exit(0);
	
	
	int y,cnt=0;
	
	float *p=new float[6];
	
	
	int d =0;
	
	for(int i=0;i<argc-1;i++){//argc-1 posisition has the Image
		string ST(argv[i]);
		if(ST.compare("gamma") == 0)
			d=1;
		else if(ST.compare("linear")==0)
			d=2;
		else if((ST.compare("-f")!=0)&&(ST.compare("gamma")!=0)&&(ST.compare("linear")!=0)){
			if(d==1){
				cout<<"Trying ..Filter Gamma";
				y=stof(ST);
				FilterGamma g(y);
				s= g << s;
				d=0;
				s.save("IMG.ppm","ppm");
				cout<<"Filter Gamma completed";
			}else if(d==2){
				p[cnt]=stof(ST);
				cnt++;
				if(cnt==6){
					cout<<"tryng ..filter Linear";
				Vec3<float> a(p[0],p[1],p[2]);
				Vec3<float> c(p[3],p[4],p[5]);
			    FilterLinear l(a,c);
				s= l << s;
				s.save("IMG.ppm","ppm");
				cnt=0;
				d=0;
				cout<<"FilterLinear completed";
				}	
			}
			
			
			
		}
		
		
	}
	

	cout<<"OK"<<endl;
	system("pause");
	exit(0);

}











