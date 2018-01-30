import java.io.*;
class Nuke2{
    public static void main(String[] arg)throws Exception{
        BufferedReader Keyboard = new BufferedReader(new InputStreamReader(System.in));
        String inputline = Keyboard.readLine();
        String first = inputline.substring(0,1);
        String last = inputline.substring(2,inputline.length());
        System.out.println(first.concat(last));
    }
}