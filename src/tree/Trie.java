package tree;


/**
 * 实现字典树数据结构，详情见注释
 * 参考博客
 * https://www.cnblogs.com/xujian2014/p/5614724.html
 */
// 字典树节点
public class Trie {
    private static int SIZE=26;
    private TrieNode root=new TrieNode();
    class TrieNode{
        private int num;// 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
        private TrieNode[] son;// 所有的儿子节点
        private boolean isEnd;// 是不是最后一个节点
        private char val;// 节点的值
        TrieNode(){
            num=1;
            son=new TrieNode[SIZE];
        }
    }
//    建立字典树
    public void insert(String str){
        if(str==null||str.length()==0){
            return;
        }
        TrieNode node=root;
        char[] letters=str.toCharArray();
        for(int i=0;i<letters.length;i++){
            int pos=letters[i]-'a';
            if(node.son[pos]==null){
                node.son[pos]=new TrieNode();
                node.son[pos].val=letters[i];
            }else{
                node.son[pos].num++;
            }
            node=node.son[pos];
        }
        node.isEnd=true;
    }
    //计算单词前缀的数量
    public int countPrefix(String prefix){
        if(prefix==null||prefix.length()==0){
            return -1;
        }
        TrieNode node=root;
        char[] letters=prefix.toCharArray();
        for(int i=0;i<letters.length;i++){
            int pos=letters[i]-'a';
            if(node.son[pos]==null){
                return 0;
            }else{
                node=node.son[pos];
            }
        }
        return node.num;
    }
    // 前序遍历字典树
    public void preTraverse(TrieNode node){
        if(node!=null)
        {
            System.out.print(node.val+"-");
            for(TrieNode child:node.son)
            {
                preTraverse(child);
            }
        }
    }
    // 在字典树中查找一个完全匹配的单词.
    public boolean has(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] != null) {
                node = node.son[pos];
            } else {
                return false;
            }
        }
        return node.isEnd;
    }

    public static void main(String[] args) {
        Trie tree=new Trie();
        String[] dictionaryData= {"hello","student","computer","sorry","acm","people","experienced","who","reminds","everyday","almost"};
        for(String s:dictionaryData){
            tree.insert(s);
        }
        System.out.println(tree.countPrefix("st"));
        System.out.println(tree.has("studenst"));
    }
}
