package org.common.util.structre;
/**
 * 常见排序算法
 * <br>Date:2016年1月13日 下午4:47:20
 * @author pujie
 */
public class Sort {

	/**
	 * 快速排序
	 * <br>Date:2016年1月13日 下午7:04:53
	 * @author pujie
	 * @param a
	 */
	public static void quickSort(int[] a){
		quickSort(a,0,a.length-1);
	}
	/**
	 * 快速排序
	 * <br>Date:2016年1月13日 下午5:53:18
	 * @author pujie
	 * @param array
	 * @param left
	 * @param right
	 */
	public static void quickSort(int[] a,int left,int right){
		if(left >= right)
			return ;
		int i=left, j=right, base=a[left],temp;
		while(i < j){
			//一定要从右边（基准数的另一边）开始，当i，j重合的时候，此位置才是基准数该放的位置
			while(a[j]>=base && i<j){//从数组右边开始遍历，一直找到小于base的数为止
				j--;
			}
			while(a[i]<=base && i<j){//从数组左边开始遍历，一直找到大于base的数为止
				i++;
			}
			
			if(i<j){
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		//将基准数归位
		a[left] = a[i];
		a[i] = base;
		quickSort(a,left,i-1);//继续处理左边的，这里是一个递归的过程
		quickSort(a,i+1,right);//继续处理右边的 ，这里是一个递归的过程
	}
	/**
	 * 对指定int数组进行<直接插入排序>
	 * <br>Date:2016年1月19日 上午11:53:55
	 * @author pujie
	 * @param a
	 */
	public static void insertSort(int[] a){
		insertSort(a,a.length);
	}
	/**
	 * 直接插入排序<br>
	 * 1. 初始时，a[0]自成1个有序区，无序区为a[1..n-1]。令i=1 <br>
	 * 2. 将a[i]并入当前的有序区a[0…i-1]中形成a[0…i]的有序区间。<br>
	 * 3. 如果a[i]>=a[i-1] 直接并入。否则，循环有序区[0...i-1]找出a[i]的正确位置，然后插入<br>
	 * Date:2016年1月19日 上午11:35:43
	 * @author pujie
	 * @param a 待排序数组
	 * @param n 待排序字符长度
	 */
	public static void insertSort(int[] a,int n){
		int temp,j;
		for(int i=1;i<n;i++){
			if(a[i] < a[i-1]){//如果a[i] < a[i-1]，则表示a[i]不能直接加入有序集合[0....i-1]。需要对a[i]进行有序从插入
				temp = a[i];
				for(j=i-1;j>0 && a[j]>temp;j--){
					a[j+1] = a[j];
				}
				a[j+1] = temp;
			}
		}
	}
	/**
	 * 折半插入查询<br>
	 * <br>Date:2016年1月22日 下午3:21:48
	 * @author pujie
	 * @param a
	 * @param n
	 */
	public static void binaryInsertSort(int[] a,int n){
		for(int i=1;i<n;i++){
			int mid,low = 0,high=i-1;
			int temp = a[i]; //待排序的数
			while(low <= high){ //查找插入点。插入点在low的位置。
				mid = (low + high)/2;
				if(temp > a[mid]){
					low = mid+1;
				}else if(temp < a[mid]){
					high = mid -1;
				}else{
					low = mid;break;
				}
			}
			for(int j=i-1;j>=low;j--){
				a[j+1] = a[j];
			}
			a[low] = temp;
		}
	}
	public static void main(String[] args) {
		int[] a= {1,30,10,6,5,2,7,9,3,7,12,4};
//		quickSort(a);
//		insertSort(a);
		binaryInsertSort(a,a.length);
		
		for(int i:a){
			System.out.print(i+" ");
		}
	}
}
