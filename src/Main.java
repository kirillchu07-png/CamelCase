class main{
    public static void main(String[] args){
        CamelCase camelCase=new CamelCase();
        String text =camelCase.input(args);
        String result = camelCase.redacting(text);
        camelCase.output(result);
    }
}