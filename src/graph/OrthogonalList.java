package graph;

/**
 * 本例实现图中的十字链表，使用十字链表来存储稀疏矩阵
 * 思想参考：http://data.biancheng.net/view/186.html
 */
public class OrthogonalList {
    private int col;  //统计列数
    private int row; //统计行数
    private Node[] rowhead;
    private Node[] colhead;

    /**
     * 节点
     */
    class Node {
        int data;
        int y;
        int x;
        Node right;
        Node down;

        public Node(int x, int y, int data) {
            this.data = data;
            this.y = y;
            this.x = x;
        }
    }

    /**
     * 压缩稀疏矩阵
     *
     * @param target 被存储的矩阵
     */
    public void compression(int[][] target) {
        col = target[0].length;
        row = target.length;
        rowhead = new Node[row];
        colhead = new Node[col];
        for (int i = 0; i < row; i++) {
            Node left = rowhead[i];
            for (int j = 0; j < col; j++) {
                if (target[i][j] != 0) {
                    Node node = new Node(i, j, target[i][j]);
                    Node up = colhead[j];
                    if (up == null) {
                        colhead[j] = node;
                    } else {
                        while (up.down != null) {
                            up = up.down;
                        }
                        up.down = node;
                    }
                    if (left == null) {
                        rowhead[i] = node;
                    } else {
                        left.right = node;
                    }
                    left = node;
                }
            }
        }
    }

    /**
     * 使用rowhead恢复原数组
     */
    public int[][] restoreByRow() {
        int[][] matrix = new int[rowhead.length][colhead.length];
        for (int i = 0; i < rowhead.length; i++) {
            Node temp = rowhead[i];
            if (temp != null) {
                while (temp != null) {
                    matrix[temp.x][temp.y] = temp.data;
                    temp = temp.right;
                }
            }
        }
        return matrix;
    }
    /**
     * 使用colhead恢复原数组
     */
    public int[][] restoreByCol() {
        int[][] matrix = new int[rowhead.length][colhead.length];
        for (int i = 0; i < colhead.length; i++) {
            Node temp = colhead[i];
            if (temp != null) {
                while (temp != null) {
                    matrix[temp.x][temp.y] = temp.data;
                    temp = temp.down;
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        OrthogonalList orthogonalList = new OrthogonalList();
        int[][] matrix = new int[][]{
                {1, 4, 0, 0},
                {0, 2, 0, 0},
                {1, 0, 2, 0},
                {0, 0, 0, 9}
        };
        orthogonalList.compression(matrix);
        int[][] array = orthogonalList.restoreByRow();
        int num = 0;//统计原始矩阵和恢复的矩阵有多少元素不同的个数
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (array[i][j] != matrix[i][j]) {
                    num++;
                }
            }
        }
        System.out.println("使用row恢复的矩阵和原矩阵不相等的个数: " + num);
        int[][] array1 = orthogonalList.restoreByCol();
        num = 0;//统计原始矩阵和恢复的矩阵有多少元素不同的个数
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (array1[i][j] != matrix[i][j]) {
                    num++;
                }
            }
        }
        System.out.println("使用col恢复的矩阵和原矩阵不相等的个数: " + num);

    }
}