import inerfaceText.*;
import java.util.Scanner;
class CamelCase implements interfaceText {
    public String input(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите слова");
        String text = scanner.nextLine();
        return text;
    }
    public void output(String text) {

        System.out.println(text);
    }
    public String redacting(String text){
        String[]arr=text.split(" ");
        StringBuilder result = new StringBuilder();
        result.append(arr[0].toLowerCase());
        for(int i=1;i<arr.length;i++){
            result.append(arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1).toLowerCase());
        }
        return result.toString();
    }

}