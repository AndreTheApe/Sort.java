package andretheape.csci210;

import java.util.*;

public class Sort {
   int swapcount = 0;
   int comparisoncount = 0;
   public void selectiveSort(String x[])
   {
      selectiveSort(x, x.length-1); 
   }
   public int findmaxima(String x[], int last)
   {
       int tempm = 0;
       for(int j=1; j <= last; j++)
       {
           comparisoncount++;
           if (x[tempm].compareTo(x[j]) < 0) //compareTo is alphabetially 
              tempm = j;    
       }
       return tempm;
   }
    
   void swap(String x[], int i, int j)
   {
      swapcount++;
      if (i != j)
      {
         String tmp = x[i];
         x[i] = x[j];
         x[j] = tmp;
      }
   }
   public void selectiveSort(String x[], int last)
   {
      if (last > 0) 
      {
        int j = findmaxima(x, last);
        swap(x, j, last);
        selectiveSort(x, last-1);
      }
   }
   
   private int comparestr(String z, String y)
   {
      comparisoncount++;
      return z.compareTo(y);
   }
   public void insertSort(String x[])
   {
      int n = x.length;
      for (int k=1; k < n; k++)
      {
          for (int i=k; i>=1 && 
               comparestr(x[i],x[i-1]) < 0; i--)
          {
              swap(x, i, i-1);
          }
      }
   }
   public static void main1(String [] args)
   {
       int N= 10000;
       Sort s = new Sort();
       String [] names = new String[N];
       for (int i=0; i < N; i++)
          names[i] = "" + Math.random();
       long t0 = System.currentTimeMillis();      
       s.comparisoncount = 0;
       s.swapcount =0;
       s.selectiveSort(names);
       long t1 = System.currentTimeMillis(); 
       long diff = t1 - t0;
       System.out.println("Time spent on sorting 1 million-element array is="
      + diff + " millseconds");
       for (String name:names)  System.out.println(name);
       System.out.println("number of comparisons:" + s.comparisoncount);
       System.out.println("number of swappings:" + s.swapcount); 
      
       s.comparisoncount = 0;
       s.swapcount =0;
       names = new 
       String[]{"Mary",  "Scott", "Nancy","Marie","John"};
     
     /* s.insertSort(names);
       for (String q:names)
          System.out.println(q);
       System.out.println("number of comparisons:" + s.comparisoncount);
       System.out.println("number of swappings:" + s.swapcount);  
        
       t0 = System.currentTimeMillis();
      Arrays.sort(names);
      //for (String q:names)   System.out.println(q);
       t1 = System.currentTimeMillis(); 
      */
       
   }
   
   void mergesort(int x[], int low, int high)
   {
      if (low >= high) return;
      int mid = (low + high)/2;
      mergesort(x, low, mid);
      mergesort(x, mid+1, high);
      merge(x, low, mid, high);
   }
   void merge(int x[], int low, int mid, int high) 
   {
      int i=low, j = mid+1, k=0;
      
      while(i <= mid && j <= high)
      { if (x[i] < x[j]){y[k] = x[i];i++;k++;}
        else {y[k] = x[j]; j++; k++;}
      } 
      if (i > mid)//the left subarray exhausted first
      {  
         k--;j--;
         for (;k>=0;k--,j--)
          x[j] = y[k];
      }
      else // right subarray exhausted, copy 
      {    //remaining of the first subarray
           
          for(; i<=mid; i++,k++) y[k] = x[i]; 
          for (k=0,i=low;k<= high -low; k++,i++)
          x[i] = y[k];
      }
      
   }
int [] y;

int RadixGetMaxLength(ArrayList<Integer> array , int arraySize) {
   int maxDigits = 0;
   for (int i = 0; i < arraySize; i++) {
      int digitCount = RadixGetLength(array.get(i));
      if (digitCount > maxDigits)
         maxDigits = digitCount;
   }
   return maxDigits;
}

// Returns the length, in number of digits, of value
int RadixGetLength(int value) 
{
   if (value == 0)
      return 1;

   int digits = 0;
   while (value != 0) {
      digits = digits + 1;
      value = value/10;
   }
   return digits;
}

int GetLowestDigit(int n)
{
    return n%10;
}

public void RadixSort(ArrayList<Integer> array, int arraySize)
{
  if (arraySize <= 1) return;
  ArrayList<Integer> [] buckets = new ArrayList[10];
  for (int i=0; i < 10; i++)
      buckets[i] = new ArrayList<Integer>();
  int maxDigits = RadixGetMaxLength(array, arraySize);
  int pow = 1; 
  int i = 1;
  while (i < maxDigits)
  {
      pow *= 10;
      i++;
  }
  for (i=0; i < arraySize; i++)
  {
      int bucketindex =  array.get(i)/pow;
      buckets[bucketindex].add(array.get(i));
  }
  for (int j=0; j < 10; j++)
  {
     int N = buckets[j].size();
     if (N>1)
     {
         for (int l=0; l < N; l++ )
             buckets[j].set(l,buckets[j].get(l).intValue() - j*pow);
         RadixSort(buckets[j], buckets[j].size());
         for (int l=0; l < N; l++ )
             buckets[j].set(l,buckets[j].get(l).intValue() + j*pow);
     }
  }
  i=0;
  array.clear();
  for (int j=0; j < 10; j++)
  {
      for (int k=0; k < buckets[j].size(); k++)
      {
         array.add(buckets[j].get(k)); 
      }
  }
    
}
// arr[i] in [0, b)
public void countsort1(int arr[], int exp)
{
    int count[] = new int[10];
    for(int i=0; i < 10; i++) count[i]=0;
    for (int i=0; i < arr.length; i++)
     count[(arr[i]/exp)%10]++; 
    int acc[] = new int[10];
    acc[0] = count[0];
    for (int i=1; i < 10; i++)
       acc[i] = count[i] + acc[i-1];
    int temp[] = new int[arr.length];
    for (int i=0; i < arr.length; i++)
    {
       int bucketNum =  arr[i];
       int accnum = acc[arr[i]];
       temp[accnum] = arr[i];
       acc[bucketNum]--;    
    }
     for (int i=0; i < arr.length; i++)
        arr[i] = temp[i];
}
public int max(int arr[])
{
    int m = arr[0];
    for (int i=1; i < arr.length; i++)
        if (m < arr[i]) m = arr[i];
    return m;
}
public void radixsort(int arr[])
{
    int m = max(arr);
    for (int exp=1; (m/exp)>0 ;exp *=10)
    {
        countsort1(arr, exp);
    }
}
   
public static void main(String [] args)
{
       int N = 100;
       Sort s = new Sort();
       ArrayList<Integer> x = new ArrayList<Integer>();
       s.y = new int[N];
       for (int i=0; i < N; i++)
       {
           x.add( (int)Math.floor(Math.random()*1000000));
       }
       
       long t0 = System.currentTimeMillis();
       // t0 = 50 x 365 x 24 x 3600 x 1000
       s.RadixSort(x,N);
       long t1 = System.currentTimeMillis();
       long dif = t1 - t0;
        
       System.out.println("time=" + dif);
       for (int n : x)   System.out.println(n);
}
}