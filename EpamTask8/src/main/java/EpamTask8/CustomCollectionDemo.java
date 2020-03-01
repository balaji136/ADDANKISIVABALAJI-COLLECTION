package EpamTask8;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class MyCustomCollection<E> extends AbstractList<E> {
	public static final Logger LOGGER=LogManager.getLogger(MyCustomCollection.class);
     Object[] customList;
    int sizeOfList=10;//Default size
    int elementsFilled=0;
   public  MyCustomCollection() {
        customList = new Object[10];
    }

   @SuppressWarnings("unchecked")
    public E get(int index) {
    	
        return (E)customList[index];
    }
   @SuppressWarnings("unchecked")
    public E set(int index, Object element) {
        E oldValue = (E)customList[index];
        customList[index] = element;
        return oldValue;
    }

    public int size() {
        return customList.length;
    }
    public boolean add(Object element){

        for(int i=0;i<sizeOfList;i++){
            if(customList[i]==null){
                elementsFilled=i;
                break;
            }
           elementsFilled=i+1;

        }
        if(elementsFilled == sizeOfList){
           Object[] customListCopy = new Object[sizeOfList];
            System.arraycopy(customList,0,customListCopy,0,sizeOfList);
            //size is exceeded then increments it by 10 at every time
            sizeOfList = sizeOfList+10;
            customList = new Object[sizeOfList];
            System.arraycopy(customListCopy,0,customList,0,customListCopy.length);
            
            customList[elementsFilled] = element;
        }
        customList[elementsFilled] = element;
        
        return true;

    }
    @SuppressWarnings("unchecked")
    public E remove(int index){
         
         E returnElement;
         if(elementsFilled>index){
         returnElement = (E)customList[index];
         for(int i=index;i<elementsFilled;i++)
            customList[i] = customList[i+1];
        elementsFilled=elementsFilled-1;
        return(returnElement);
          }
        else
            return null;

    }
    public void print(){
        
        for(int i=0;i<=elementsFilled;i++)
            LOGGER.info(customList[i]);
    }

}
public class CustomCollectionDemo{
	public static final Logger LOGGER=LogManager.getLogger(CustomCollectionDemo.class);
    public static void main(String[] args){
        MyCustomCollection<Integer> collectionList = new MyCustomCollection<Integer>();
        LOGGER.info("Adding Elements in to custom collections");
        for(int i=1;i<13;i++){
            collectionList.add(i);
        }
        LOGGER.info("Elements present in the collection before removal");
        collectionList.print();
        LOGGER.info("5th Element in the Collection:"+collectionList.remove(5));
        LOGGER.info("2nd Element in the collection:"+collectionList.get(1));
        LOGGER.info("Collecton after removal");
        collectionList.print();
        
    }
}

