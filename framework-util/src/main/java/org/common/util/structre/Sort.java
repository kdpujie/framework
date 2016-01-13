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
	
	public static void main(String[] args) {
		int[] a= {30,10,1,5,2,7,9,3,7,12,4};
		quickSort(a);
		for(int i:a){
			System.out.print(i+" ");
		}
	}
}
