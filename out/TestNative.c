#include "my_nativ_TestNative.h"
#include <String.h>
JNIEXPORT jstring JNICALL Java_my_nativ_TestNative_addHeadString
(JNIEnv *env, jclass jcl, jstring jstr)
{
 const char *cstr = (*env)->GetStringUTFChars(env,jstr,0);
 //获取参数jstr的指针；之后通过cstr就可以访问jstr中的数据；最后一个参数是isCopy？
 jsize size = (*env)->GetStringUTFLength(env,jstr);
 //获取String数据的长度
 char buff[256] = {0}; //注意C语言必须要将所有的类型声明放在函数最开始的位置！
 if(cstr==NULL){
  printf("out of memory \n");
 }
 (*env)->ReleaseStringUTFChars(env,jstr,cstr);
 //释放本函数生成的指向jstr的指针，类似于将cstr置空
 //该方法与GetStringUTFChars方法成对出现
 sprintf(buff,"evan %s %d",cstr ,size); //这是一个C方法，用于打印字符串到有一个字符数组当中
 return (*env)->NewStringUTF(env,buff);
 //这里是创建一个新的jstring对象，新对象的值由buff决定,长度是buff实际的值；即一般是小于256
 
}
JNIEXPORT jint JNICALL Java_my_nativ_TestNative_sum
  (JNIEnv * env, jclass jcl, jint a, jint b)
{
 return  a+b;
}
 
 
JNIEXPORT jintArray JNICALL Java_my_nativ_TestNative_improve
  (JNIEnv *env, jclass jcl, jintArray array,jint size)
{
 jint* intarray = (*env)->GetIntArrayElements(env,array,0);
 //本地获取到一个访问java堆中数组的指针
 jintArray data = (*env)->NewIntArray(env,size);
 //生成一个长度为size的IntArray数组
 int index = 0;
 jint buff[100];
 int length=0;
 if(size<100) length=size;
 else length =100;
 if(data==NULL){
  printf("out of memory \n");
 }
 for(;index<length;index++){
  intarray[index]++;
  //这里虽然改了这个指针所指向的数据，
  //但是在java堆中所存储的数组并不会改变,即java类中定义的数组并不会发生变化
  buff[index] = 2+intarray[index];
 }
 (*env)->ReleaseIntArrayElements(env,array,intarray,0);
 //释放intarray指针
 (*env)->SetIntArrayRegion(env,data, 0,length,buff);
 //将buff的数据复制到data中
 return data;
}