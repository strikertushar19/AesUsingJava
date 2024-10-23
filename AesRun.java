
class Aes{
    byte [][] key=new byte[4][4];
    byte[][] matrix=new byte[4][4];
    // byte[][] Sbox=new byte[16][16];
    byte[][] Sbox = {
        {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'},
        {'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f'},
        {'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'},
        {'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'},
        {'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b'},
        {'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r'},
        {'s', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'},
        {'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X'},
        {'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
        {'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D'},
        {'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'},
        {'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
        {'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
        {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'},
        {'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f'},
        {'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'}
    };


/*  I can setup using this function as well  then i need to just declare which is donw above initialization
    void setupSbox(){
        for (int row = 0; row < 16; row++) {
            if (row < 4) {
                // First four rows: value 4
                for (int col = 0; col < 16; col++) {
                    Sbox[row][col] = 66; // Set each square similarly others
                }
            } else if (row < 8) {
                // Next four rows: value 5
                for (int col = 0; col < 16; col++) {
                    Sbox[row][col] = 85; 
                }
            } else if (row < 12) {
                // Next four rows: value 6
                for (int col = 0; col < 16; col++) {
                    Sbox[row][col] = 89; 
                }
            } else {
                // Last four rows: value 7
                for (int col = 0; col < 16; col++) {
                    Sbox[row][col] = 90; 
                }
            }
        }
    }
         */

    void shiftRows(){
            // row 1 would remain same.....

        // 2nd row left shift by one;
        byte shift=0;
        byte rowend=matrix[1][0];
        for(int i=1;i<matrix.length;i++){

            shift=matrix[1][i];
            matrix[1][i-1]=shift;
        }
        matrix[1][matrix.length-1]=rowend;

         shift=0;

        //third row left shift by two 
        shift=matrix[2][2];
        matrix[2][2]=matrix[2][0];
        matrix[2][0]=shift;
        shift=matrix[2][3];
        matrix[2][3]=matrix[2][1];
        matrix[2][1]=shift;

        shift=0;
        byte rowstart=matrix[3][3];

        //4th row left shift by three
        for(int i=matrix.length-1;i>0;i--){

            shift=matrix[3][i-1];
            System.out.println(i);
            matrix[3][i]=shift;
        }
        matrix[3][0]=rowstart;

    }



    void printSbox(){

    
        for (int i = 0; i < Sbox.length; i++) {
            for (int j = 0; j < Sbox[i].length; j++) {
                System.out.print(Sbox[i][j] + " "); 
            }
            System.out.println(); 
        }
        
    }

    void setupMatrix(){
        String input = "TusharIsHackerTo";
        int index = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (index < input.length()) {
                    matrix[i][j] = (byte) input.charAt(index);
                    index++;
                } else {
                    matrix[i][j] = (byte) 'x';
                }
            }
        }
    }
    void printMatrix(){
        StringBuilder output = new StringBuilder();
    
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                output.append((char) matrix[i][j]);
                System.out.print((char) matrix[i][j] + " ");
            }
            System.out.println();
           
        }
        System.out.println(output.toString());        
    }
    void substituteBytes(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){

                byte value=matrix[i][j];
                byte first4bits=(byte)(value & 0x0F);
                byte last4bits=(byte)((value >> 4) & 0x0F);
                byte newValue=Sbox[first4bits & 0x0F][last4bits & 0x0F];
                matrix[i][j]=newValue;


            }
        }

    }



}



 class AesRun{
    public static void main(String[] args) {
        System.out.println("Hello from PrimeChecker!");
        Aes  obj= new Aes();
        obj.setupMatrix();
        obj.printMatrix();
        obj.shiftRows();
        obj.printMatrix();

        // System.out.println("output before substitution" );
        // obj.substituteBytes();
        // System.out.println("output after substitution" );
        // obj.printMatrix();


    }
}