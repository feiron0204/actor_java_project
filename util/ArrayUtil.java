package util;


// 배열을 사용하는데 도움이 되는 다양한 메소드를 모아둔 클래스
public class ArrayUtil {
    // 1. int[]
    // A. 현재 배열의 크기가 얼마인지 알려주는 size()
    public static int size(int[] arr) {
        return arr.length;
    }

    // B. 현재 배열의 특정 인덱스에 저장된 값을 리턴해주는
    // get()
    public static int get(int[] arr, int index) {
        return arr[index];
    }

    // C. 현재 배열이 비어있는지 아닌지를 알려주는
    // isEmpty()
    public static boolean isEmpty(int[] arr) {
        return size(arr) == 0;
    }

    // D. 현재 배열에 특정 값이 존재하는지 리턴해주는
    // contains()
    public static boolean contains(int[] arr, int e) {
        for (int i = 0; i < size(arr); i++) {
            if (get(arr, i) == e) {
                return true;
            }
        }
        return false;
    }

    // E. 현재 배열에 특정 값이 가장 먼저 등장하는 인덱스가 얼마인지 리턴해주는
    // indexOf()
    // 단, 해당 배열에 그 특정값이 존재하지 않을 시에는 -1이 리턴된다.
    public static int indexOf(int[] arr, int e) {
        for (int i = 0; i < size(arr); i++) {
            if (get(arr, i) == e) {
                return i;
            }
        }
        return -1;
    }

    // F. 현재 배열에 특정 값의 가장 마지막에 등장하는 인덱스가 얼마인지 리턴에주는
    // lastIndexOf()
    // 단, 해당 배열에 그 특정 값이 존재하지 않을 시에는 -1이 리턴된다.
    public static int lastIndexOf(int[] arr, int e) {
        for (int i = size(arr) - 1; i >= 0; i--) {
            if (get(arr, i) == e) {
                return i;
            }
        }
        return -1;
    }

    // G. 현재 배열에 맨 마지막 칸 뒤에 새로운 요소가 추가되어
    // 크기가 1 늘어난 배열을 리턴해주는
    // add()
    public static int[] add(int[] arr, int e) {
        int[] temp = new int[size(arr) + 1];
        for (int i = 0; i < size(arr); i++) {
            temp[i] = get(arr, i);
        }
        temp[size(temp) - 1] = e;

        return temp;
    }

    // H. 현재 배열에 특정 인덱스에 새로운 값을 추가하고
    // 기존에 있던 값들은 한칸씩 뒤로 밀려난
    // 배열을 리턴해주는 add()
    public static int[] add(int[] arr, int index, int e) {
        int[] temp = new int[0];
        for (int i = 0; i < index; i++) {
            temp = add(temp, get(arr, i));
        }
        temp = add(temp, e);
        for (int i = index; i < size(arr); i++) {
            temp = add(temp, get(arr, i));
        }
        return temp;
    }

    // I. 특정 인덱스의 값을 다른 값으로 교체하고
    // 기존 값을 리턴해서 필요하다면 추가적으로 처리를 할 수 있게하는
    // set()
    public static int set(int[] arr, int index, int e) {
        int temp = get(arr, index);
        arr[index] = e;

        return temp;
    }

    // J. 특정 인덱스를 삭제하는
    // removeByIndex()
    // 단, 유효하지 않은 인덱스가 넘어올 경우, 아무것도 삭제되지 않은
    // 원래 배열이 리턴된다.
    public static int[] removeByIndex(int[] arr, int index) {
        if (!(index >= 0 && index < size(arr))) {
            return arr;
        }
        int[] temp = new int[0];
//        for(int i=0;i<index;i++) {
//            temp=add(temp,get(arr,i));
//            
//        }
//        for(int i=index+1;i<size(arr);i++) {
//            temp=add(temp,get(arr,i));
//        }
        for (int i = 0; i < size(arr); i++) {
            if (i != index) {
                temp = add(temp, get(arr, i));

            }
        }
        return temp;
    }

    // K. 파라미터로 들어온 값을 배열에서 삭제하는
    // removeByElement()
    public static int[] removeByElement(int[] arr, int e) {
        // int index = indexOf(arr,e);
        // return removeByIndex(arr,index);
        // 굳이?

        // int[] temp=removeByIndex(arr,index);
        // return temp;
        // 굳이??????????
        return removeByIndex(arr, indexOf(arr, e));
        /*
         * 해당 메소드가 실행되게 되면 indexOf 가 먼저 실행됨->int 값 받음 이후 removeByIndex(arr,int값) 실행
         * ->int 배열 결과 값 받음 배열을 그대로 return해줌->추가적인 변수지정이 필요가없다.
         */
    }

    // 2. String[]
    // A. 현재 배열의 크기가 얼마인지 알려주는 size()
    public static int size(String[] arr) {
        return arr.length;
    }

    // B. 현재 배열의 특정 인덱스에 저장된 값을 리턴해주는
    // get()
    public static String get(String[] arr, int index) {
        return arr[index];
    }

    // C. 현재 배열이 비어있는지 아닌지를 알려주는
    // isEmpty()
    public static boolean isEmpty(String[] arr) {
        return size(arr) == 0;
    }

    // D. 현재 배열에 특정 값이 존재하는지 리턴해주는
    // contains()
    public static boolean contains(String[] arr, String e) {
        for (int i = 0; i < size(arr); i++) {
            if (get(arr, i).equals(e)) {

                return true;
            }
        }
        return false;
    }

    // E. 현재 배열에 특정 값이 가장 먼저 등장하는 인덱스가 얼마인지 리턴해주는
    // indexOf()
    // 단, 해당 배열에 그 특정값이 존재하지 않을 시에는 -1이 리턴된다.
    public static int indexOf(String[] arr, String e) {
        for (int i = 0; i < size(arr); i++) {
            if (get(arr, i).equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // F. 현재 배열에 특정 값의 가장 마지막에 등장하는 인덱스가 얼마인지 리턴에주는
    // lastIndexOf()
    // 단, 해당 배열에 그 특정 값이 존재하지 않을 시에는 -1이 리턴된다.
    public static int lastIndexOf(String[] arr, String e) {
        for (int i = size(arr) - 1; i >= 0; i--) {
            if (get(arr, i).equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // G. 현재 배열에 맨 마지막 칸 뒤에 새로운 요소가 추가되어
    // 크기가 1 늘어난 배열을 리턴해주는
    // add()
    public static String[] add(String[] arr, String e) {
        String[] temp = new String[size(arr) + 1];
        for (int i = 0; i < size(arr); i++) {
            temp[i] = get(arr, i);
        }
        temp[size(temp) - 1] = e;

        return temp;
    }

    // H. 현재 배열에 특정 인덱스에 새로운 값을 추가하고
    // 기존에 있던 값들은 한칸씩 뒤로 밀려난
    // 배열을 리턴해주는 add()
    public static String[] add(String[] arr, int index, String e) {
        String[] temp = new String[0];

//        for(int i=0;i<index;i++) {
//            temp=add(temp,get(arr,i));
//        }
//        temp=add(temp,e);
//        for(int i=index;i<size(arr);i++) {
//            temp=add(temp,get(arr,i));
//        }
        /*
         * 요게 더 짧은 코드
         */
        for (int i = 0; i < size(arr); i++) {
            if (i == index) {
                temp = add(temp, e);
            }
            temp = add(temp, get(arr, i));
        }
        return temp;
    }

    // I. 특정 인덱스의 값을 다른 값으로 교체하고
    // 기존 값을 리턴해서 필요하다면 추가적으로 처리를 할 수 있게하는
    // set()
    public static String set(String[] arr, int index, String e) {
        /*
         * temp 선언이유: 기존값이 필요할수도있걸랑...swap같은거할때
         */
        String temp = get(arr, index);
        arr[index] = e;

        return temp;
    }

    /*
     * int의 경우 2번째 파라미터에 이게 인덱스인지 값인지 애매모호해서 2개로 나눴찌만 String은 오버로딩가능 숫자면 인덱스고 스트링이면
     * 값 제거
     */
    // J. 특정 인덱스를 삭제하는
    // remove()
    // 단, 유효하지 않은 인덱스가 넘어올 경우, 아무것도 삭제되지 않은
    // 원래 배열이 리턴된다.
    public static String[] remove(String[] arr, int index) {
        if (!(index >= 0 && index < size(arr))) {
            return arr;
        }
        String[] temp = new String[0];
        for (int i = 0; i < size(arr); i++) {
            if (i != index) {
                temp = add(temp, get(arr, i));

            }
        }
        return temp;
    }

    // K. 파라미터로 들어온 값을 배열에서 삭제하는
    // remove()
    public static String[] remove(String[] arr, String e) {

        return remove(arr, indexOf(arr, e));
    }
}