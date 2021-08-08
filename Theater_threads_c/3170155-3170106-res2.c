
#include "p3170155-p3170106-res2.h"

#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>



//int counter_seats=0;
int current_til=Ntel;
int current_tam=Ncash;
int boith=0;
int* zoneA;
int* zoneB;
int* zoneC;

int global=0;

int* pelA;
int* pelB;
int* pelC;
unsigned int seed;
int bank_account=0;
double alltime=0;//mesos xronos tilefonimatos
double sumall=0; //mesos xronos anamonis
double c=0; //testing

int sf_no_seats=0,sf_no_pay=0,sf_ok=0;


void * phone_call(void*);




pthread_mutex_t Wait_N;// =PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t BA;// =PTHREAD_MUTEX_INITIALIZER;

pthread_cond_t condionair;// ;
pthread_cond_t condionair2;
pthread_cond_t condionair3;

pthread_mutex_t TH;// =PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t TAM;

struct timespec start,end,start2,end2;





void* phone_call(void* arg1){
    int thesi=0;
    global++;
    printf("pelatis %d \n",*((int*)arg1));
   	

    clock_gettime(CLOCK_REALTIME, &start);
    pthread_mutex_lock(&Wait_N);
    while (current_til<1){
        pthread_cond_wait(&condionair, &Wait_N);
    }
    printf("The current til that you are speaking is :%d\n",current_til);
    current_til--;
  pthread_mutex_unlock(&Wait_N);

    clock_gettime(CLOCK_REALTIME, &end);

   
    sumall+=end.tv_sec-start.tv_sec;
    alltime+=end.tv_sec-start.tv_sec;
    
    clock_gettime(CLOCK_REALTIME, &start);

  
    int numseat;
    int i,j;
  
    numseat=rand_r(&seed)%Nseathigh; //the customer decides how many seats he wants
    int price;
    if(numseat<Nseatlow){
      numseat+=Nseatlow;
    }
   

    unsigned int zone=(rand_r(&seed)%100)+1; //the customer decides the zone
    if (zone<=PzoneA*100){
      zone=1;
    }else if((zone<=PzoneB*100 + PzoneA*100) && (zone>=PzoneA*100+1)){
      zone=2;
    }else{
      zone=3;
    }


    unsigned int waiting_t=rand_r(&seed)%tseathigh;
    if(waiting_t<tseatlow){
      waiting_t+=tseatlow;
    }
    sleep(waiting_t);//Wait for the til to arrange your seats
    
    int f=0;
    int seir;

    pthread_mutex_lock(&TH);
      //seira i thesi j      
      
      if(zone==1){ // Desmeuo tis theseis

        for(i=0;i<NzoneA;i++)
          if(zoneA[i]+numseat<=Nseat){//gia kathe seira
            f=1;
            seir=i; //remember the seir
	    boith=(1+zoneA[i]);
            for(j=0;j<numseat;j++){
	       zoneA[i]++;
               pelA[i*Nseat+boith+j]=*((int*)arg1);
               
            }
          thesi=zoneA[i];
          break;
          }
          
      }else if(zone==2){
        
        for(i=0;i<NzoneB;i++)
          if(zoneB[i]+numseat<=Nseat){
            f=1;
            seir=i;
	    boith=zoneB[i]+1;
            for(j=0;j<numseat;j++){
               zoneB[i]++;
               pelB[i*Nseat+boith+j]=*((int*)arg1);
              
            }
           thesi=zoneB[i];
          break;
          }
      
      }else{
          for(i=0;i<NzoneC;i++)
          if(zoneC[i]+numseat<=Nseat){
            f=1;
            seir=i;
	    boith=zoneC[i]+1;
            for(j=0;j<numseat;j++){
	      zoneC[i]++;
              pelC[i*Nseat+boith+j]=*((int*)arg1);
              
            }
          thesi=zoneC[i];
          break;
          }
       
      }
    if(f==0){
      printf("Sorry ,there are no available seats in the zone %d",zone);
    }else{
      printf("The seats are yours now you have to pay .Wait..for an available Tamia");
    }
    pthread_mutex_unlock(&TH);

    if(f==1){ //All of that happen if there are available seats
          //  clock_gettime(CLOCK_REALTIME, &start2);
          
            pthread_mutex_lock(&TAM);
            while (current_tam<1){
                pthread_cond_wait(&condionair3, &TAM);
            }
            printf("There are %d available Tamias :\n",current_tam);
            current_tam--;
            pthread_mutex_unlock(&TAM);

         //  clock_gettime(CLOCK_REALTIME, &end2);
           sumall+=end2.tv_sec-start2.tv_sec;
          
          waiting_t=rand_r(&seed)%tcashhigh;
            if(waiting_t<tcashlow){
              waiting_t+=tcashlow;
            }
          sleep(waiting_t);//You have to wait waiting_t seconds for the tamias to complete the transaction
          
          int P=Pcardsucces*100;
          int Pith=(rand_r(&seed)%100)+1;

         // 
          if(Pith<P){
            if(zone==1)
              price=numseat*CzoneA;
            else if(zone==2)
              price=numseat*CzoneB;
            else 
              price=numseat*CzoneC;
            bank_account+=price;
            printf("You have succesfully purchased %d tickets in the zone %d, the code of the exchange is %d and your seats are the followings ",numseat,zone,*((int*)arg1));
          }else{
            printf("The transaction has failed and the theater will get %d  seats back customer %d itan sto shmeio %d",numseat,*((int*)arg1),thesi);
            f=2;
	pthread_mutex_lock(&BA);
            if(zone==1){

              boith=thesi;
              for(i=numseat;i>0;i--){

                zoneA[seir]--;
               

                pelA[seir*Nseat+boith]=0;
                 boith--;
              }

            }else if(zone==2){

             boith=thesi;
              for(i=numseat;i>0;i--){
              
                zoneB[seir]--;
                pelB[seir*Nseat+boith]=0;
                boith--;

              }

            }else{
                
             
             boith=thesi;
              for(i=numseat;i>0;i--){
                
                zoneC[seir]--;
                pelC[seir*Nseat+boith]=0;
                 boith--;
              }

	 //pthread_mutex_unlock(&BA);


            }
      pthread_mutex_unlock(&BA);

     

       //printf("eimai ston pelati %d\n",*((int*)arg1));


      }
       pthread_mutex_lock(&TAM);
      current_tam++;
          if (current_tam>0)
                pthread_cond_signal(&condionair3);

       pthread_mutex_unlock(&TAM);


    }

    

    pthread_mutex_lock(&Wait_N);
    
    current_til++;
    if (current_til >0)
          pthread_cond_signal(&condionair);

    pthread_mutex_unlock(&Wait_N);

    clock_gettime(CLOCK_REALTIME, &end);
    alltime+=end.tv_sec-start.tv_sec;

  if(f==0)
    sf_no_seats++;
  if(f==1)
    sf_ok++;
  if(f==2)
   sf_no_pay++;

   pthread_exit(arg1);
}

int main(int args,char** argv){
    int cust=atoi(argv[1]);
    seed=atoi(argv[2]);
    int i=0;

     

    //theater=(int*) malloc(Nseat*sizeof(int));
    zoneA=(int*) malloc(NzoneA*sizeof(int));
    zoneB=(int*) malloc(NzoneB*sizeof(int));
    zoneC=(int*) malloc(NzoneC*sizeof(int));


    pelA=malloc(sizeof(int)*NzoneA*Nseat);
   
    pelB=malloc(sizeof(int)*NzoneB*Nseat);
 

    pelC=malloc(sizeof(int)*NzoneC*Nseat);
     printf("%d",zoneA[1]);




   
    /*for(i=0;i<NzoneA/2;i++){
      zoneA[i]=0;
    }
    for(i=0;i<NzoneB;i++){
      zoneB[i]=-1;
    }
    for(i=0;i<NzoneC;i++){
      zoneC[i]=-1;
    }*/

 
  
    int eks_pel=1;//amount of customers that have completed their phonecall
    int rc;
    pthread_t* threads=malloc(cust * sizeof(pthread_t));
    
    int* threadid=(int*)malloc(sizeof(int)*cust);
    while(eks_pel<=cust){
    	 
   		
           threadid[i]=i+1;
           rc=pthread_create(&threads[i],NULL,phone_call,&threadid[i]);
		   if (rc != 0) {
    			printf("ERROR: return code from pthread_create() is %d\n", rc);
       			exit(-1);
       		   }
            eks_pel++;
           
            i++;
      
    }
    
   
    
  void *status;
    
	for (i = 0; i < cust; i++) {
		   
		rc = pthread_join(threads[i], &status);
		
		if (rc != 0) {
			printf("ERROR: return code from pthread_join() is %d\n", rc);
			exit(-1);		
		}
  	}
/*
     for(i=0;i<Nseat;i++){
	printf("the seat %d belongs to the customer %d \n",i,theater[i]);
	}
*/
    rc= pthread_mutex_destroy(&Wait_N);
    if(rc!=0){
    	printf("Error :return code from pthread_mutex_destroy() ths Wait is %d\n",rc);
	exit(-1); 
    }

    rc= pthread_mutex_destroy(&BA);
    if(rc!=0){
    	printf("Error :return code from pthread_mutex_destroy() ths trapezas is %d\n",rc);
	exit(-1); 
    }	

    rc= pthread_mutex_destroy(&TH);
    if(rc!=0){
    	printf("Error :return code from pthread_mutex_destroy() ths TH is %d\n",rc);
	exit(-1); 
    }

     rc= pthread_mutex_destroy(&TAM);
    if(rc!=0){
    	printf("Error :return code from pthread_mutex_destroy() ths Tamias is %d\n",rc);
	exit(-1); 
    }
    
    int j;
	

    for(i=0;i<NzoneA;i++)
      for(j=0;j<Nseat;j++)
        printf("Zone A,seira %d seat %d,customer %d\n",i,j,pelA[i*Nseat+j]);
    
    for(i=0;i<NzoneB;i++)
      for(j=0;j<Nseat;j++)
        printf("Zone B,seira %d ,seat %d,customer %d\n",i,j,pelB[i*Nseat+j]);

    for(i=0;i<NzoneC;i++)
      for(j=0;j<Nseat;j++)
        printf("Zone C seira %d,seat %d,customer %d\n",i,j,pelC[i*Nseat+j]);

   
	
    printf("The average time a customer has to wait to for a caller and a tamia %lf",sumall/cust);
    printf("The average time of a phonecall %lf",alltime/cust);
    printf("The theater has earned the amount of %d euros",bank_account);

    printf("%d percent of the customers were successful and now have the seats that the requested",((sf_ok/cust)*100));
    printf("%d percent of the customers didnt find a seat in the zone they have requested",((sf_no_seats/cust)*100));
    printf("%d percent of the customers failed to pay",((sf_no_pay/cust)*100));

    free(threads);
    free(zoneA);
    free(zoneB);
    free(zoneC);
   

    free(pelA);


    free(pelB);

    free(pelC);

    //free(theater);

    return 0;
}
