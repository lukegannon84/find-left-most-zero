import java.util.*;

class FindLeftmostZero{
	static final int N=100;
	public static void main(String args[]){
		
		int nThreads =Runtime.getRuntime().availableProcessors();
		
		int data[]=new int[N];
		int index[]=new int[nThreads+1];
		
		for(int j=0;j<N;j++){//populate array
			data[j]=(int)(Math.random()*N);
			
		}
		
		for(int j=0;j<=nThreads;j++){//divide workload depending on processors
			index[j]=(j*N)/nThreads;
		}
		Found fnd =new Found();
		leftMostZero workers[]=new leftMostZero[nThreads];
		
		for(int j=0;j<nThreads;j++){
			workers[j]=new leftMostZero(data,index[j],index[j+1]);
			workers[j].start();
		}
		
		try{
			for(int j=0;j<nThreads;j++)
				workers[j].join();				
				
		}
		catch(InterruptedException e){}
		
			for(int j=0;j<data.length;j++)	System.out.println(j+"-- "+data[j]);
		
		
	}
}
class Found{
	private boolean found=false;
	public void set(){found=true;}
	public boolean found(){return found;}
	public String toString(){return found+" ";}
}
class leftMostZero extends Thread{
	
		int data[];
		int lb;
		int ub;
		int index=0;
		private Found fnd;
		
	
	public leftMostZero(int d[],int k,int n){
		data=d;lb=k;ub=n;
	}
	

	public void run(){
		
		
		int k=lb;
		
		while(k<data.length&&!fnd.found()){
			
			if(data[k]==0)fnd.set();
			k++;			
		}
		
		
			
	}

	
	
}