
#include <vector>
#include <string>
#include "ppm.h"
using namespace std;
namespace math
{


	template<typename T> class Array
	{

	public:
		                                             
	protected:
		vector<T> buffer;                //! Holds the image data.
        
		unsigned int width,height;		                 //! The height of the image (in pixels)

	public:
		// metric accessors
		
		/*! Returns the width of the image
		 */
		const unsigned int getWidth() const {return width;}      
		
		/*! Returns the height of the image
		 */
		const unsigned int getHeight() const {return height;}   

		// data accessors
		
		/*! Obtains a pointer to the internal data.
		 *
		 *  This is NOT a copy of the internal image data, but rather a pointer 
		 *  to the internally allocated space, so DO NOT attempt to delete the pointer. 
		 */
		vector<T> getRawDataPtr(){
			return buffer;
		}

		/*! Obtains the color of the image at location (x,y).
		 *
		 *  The method should do any necessary bounds checking. 
		 *
		 *  \param x is the (zero-based) horizontal index of the pixel to get. 
		 *  \param y is the (zero-based) vertical index of the pixel to get. 
		 *
		 *  \return The color of the (x,y) pixel as a Color object. Returns a black (0,0,0) color in case of an out-of-bounds x,y pair.
		 */
		T getElement(unsigned int x, unsigned int y) const{
			int e=0;
			if((x>width)||(y>height)){
				cout<<"Out of bounds";
				T a(0, 0, 0);
				return a;
			}else{
				return buffer[width*y+x];
				
			}
			return T(0,0,0);
					
			
			
		}

		// data mutators
		
		/*! Sets the RGB values for an (x,y) pixel.
		 * 
		 *  The method should perform any necessary bounds checking.
		 *  
		 *  \param x is the (zero-based) horizontal index of the pixel to set. 
		 *  \param y is the (zero-based) vertical index of the pixel to set. 
		 *  \param value is the new color for the (x,y) pixel.
		 */
		void setElement(unsigned int x, unsigned int y, T & value){
			int e=0;
			
			if((x>width)||(y>height)){
				cout<<"Out of bounds";
				
			}else{
				cout<<"ok";
				buffer[y*width+x]=value;
				cout<<"done";
			}
			return;
			
			
			
			
		} 
		
		
		void addTobuf(T &v){
			buffer.push_back(v);			
		}
	
		
		                                                         
		/*! Copies the image data from an external raw buffer to the internal image buffer. 
		 *
		 *  The member function ASSUMES that the input buffer is of a size compatible with the internal storage of the 
		 *  Image object and that the data buffer has been already allocated. If the image buffer is not allocated or the 
		 *  width or height of the image are 0, the method should exit immediately.
		 *
		 *  \param data_ptr is the reference to the preallocated buffer from where to copy the data to the Image object.
		 */
		void setData(const vector<T> & data_ptr){
			for(int i=0;i<width*height;i++)
				buffer[i]=data_ptr[i];
		}         

		// constructors and destructor

		/*! Default constructor.
		 * 
		 * By default, the dimensions of the image should be zero and the buffer must be set to nullptr.
		 */
		Array<T>(){
			width=0;
			height=0;
		}		
		
		/*! Constructor with width and height specification.
		 * 
		 * \param width is the desired width of the new image.
		 * \param height is the desired height of the new image.
		 */ 
		Array<T>(unsigned int width, unsigned int height){
			this->width=width;
			this->height=height;
	
			
			
		}
		
		/*! Constructor with data initialization.
		 * 
		 * \param width is the desired width of the new image.
		 * \param height is the desired height of the new image.
		 * \param data_ptr is the source of the data to copy to the internal image buffer.
		 * 
		 * \see setData
		 */ 
		Array<T>(unsigned int width, unsigned int height,const vector<T> * data_ptr){
			this->width=width;
			this->height=height;
			buffer=*data_ptr;
			
			
		}
		
		/*! Copy constructor.
		 *
		 * \param src is the source image to replicate in this object.
		 */
		
		Array<T>(const Array &src){
			width=src.width;
			height=src.height;
			buffer=src.buffer;
					
			
		}
		
	
		T getEl(int cnt)const{
			return buffer[cnt];
			
		}
		
		
		/*! The Image destructor.
		 */
		~Array<T>(){
			buffer.clear();
			
		}

		/*! Copy assignment operator.
		 *
		 * \param right is the source image.
		 */
		
		Array& operator = (const Array & right){
			if(!buffer.empty())
					for(int i=0;i<width*height;i++)
						this->buffer[i]=right.buffer[i];
			this->width=right.width;
			this->height=right.height;
			return *this;
			
		}
		
		
		T& operator()(int x, int y){
			return buffer[y*width+x];
		}

		
	
	};

} 
