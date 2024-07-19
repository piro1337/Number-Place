import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * NumberPlaceクラスは、9行9列のナンプレ（ナンバープレース）に関するメソッドを含んでいます。
 * すべてのメソッドは{@code int[][]}を引数として受け取り、これを9行9列のナンプレにみたてています。要素の中の0はナンプレの問題でいう空白です。
 * すべてのメソッドは受け取る{@code int[][]}がもし9行9列に満たない場合、満たない箇所は0として扱います。もし9行9列よりも大きい配列の場合は
 * 1行目の1列目から9行目の9列目をナンプレとします。
 * すべてのメソッドは渡された{@code int[][]}型の中身を書き換えることはありません。
 */
public class NumberPlace {

    private static final int[][] KUMI = {
        {1,2,0,4,5,3,7,8,6},
        {2,0,1,5,3,4,8,6,7}
    };

    /**
     * ナンプレの答えを{@code int[][]}型で返します。
     * もし{@code null}を渡した場合は{@code null}を返します。
     * もし解が存在しない場合は{@code null}を返します。
     * もしナンプレ内に0未満、または9より大きい値が含まれていた場合{@code null}を返します。
     * @param board ナンプレにみたてた9行9列の{@code int[][]}型。
     * @return ナンプレの答えが入った{@code int[][]}型。
     */
    public static int[][] anser(int[][] board){
        if(Objects.isNull(board))return null;
        if(!judgeAll(board))return null;
        
        int row = 0;
        int col = 0;
        int count = 1;
        int[][] returnboard = new int[9][9];
        int[][] copyboard = new int[9][9];
        int boardrow = board.length>9?9:board.length;
        int[] boardcol = new int[9];

        for(int i=0; i<boardrow; i++){
            boardcol[i] = board[i].length;
        }
        for(int i=0; i<boardrow; i++){
            for(int j=0; j<boardcol[i]; j++){
                copyboard[i][j] = board[i][j];
                returnboard[i][j] = board[i][j];
            }
        }
        if(!check(copyboard))return null;
        while(true) {
            if(copyboard[row][col] == 0) {
                returnboard[row][col] = count;
                if(!judge(returnboard,row,col)) {
                    count++;
                }else {
                    col++;
                    count = 1;
                }
            }else {
                col++;
            }
            if(count==10) {
                returnboard[row][col] = 0;
                while(true) {
                if(row == -1){
                    return null;
                }
                    if(col==0) {
                        row--;
                        col=9;
                    }else{
                        col--;
                        if(copyboard[row][col]==0) {
                            if(returnboard[row][col]==9) {
                                returnboard[row][col] = 0;
                                continue;
                            }
                            count = returnboard[row][col]+1;
                            break;
                        }
                    }
                }
            }
            if(col==9) {
                row++;
                col=0;
                count=1;
            }
            if(row==9) {
                return returnboard;
            }
        }
    }

    /**
     * ナンプレの答えを{@code List<int[][]>}型として複数個返します。
     * もし{@code null}を渡した場合は{@code null}を返します。
     * もしナンプレ内に0未満、または9より大きい値が含まれていた場合{@code null}を返します。
     * もし指定した数よりもナンプレの答えが少なかった場合、答えの数にあったサイズの{@code List<int[][]>}が返ります。
     * もし答えが一つもないナンプレを渡した場合は{@code null}が返ります。
     * @param board ナンプレにみたてた9行9列の{@code int[][]}型。
     * @param x 求める答えの数
     * @return ナンプレの答えが入った{@code List<int[][]>}
     */
    public static List<int[][]> ansers(int[][] board, int x){
        if(Objects.isNull(board))return null;
        if(!judgeAll(board))return null;
        int[][] returnboard = new int[9][9];
        List<int[][]> list = new ArrayList<>();
        int row = 0;
        int col = 0;
        int count = 1;
        int[][] copyboard = new int[9][9];
        int boardrow = board.length>9?9:board.length;
        int[] boardcol = new int[9];

        for(int i=0; i<boardrow; i++){
            boardcol[i] = board[i].length;
        }
        for(int i=0; i<boardrow; i++){
            for(int j=0; j<boardcol[i]; j++){
                copyboard[i][j] = board[i][j];
                returnboard[i][j] = board[i][j];
            }
        }
        if(!check(copyboard))return null;

        for(int i=0; i<x; i++){
            while(true) {
                if(copyboard[row][col] == 0) {
                    returnboard[row][col] = count;
                    if(!judge(returnboard,row,col)) {
                        count++;
                    }else {
                        col++;
                        count = 1;
                    }
                }else {
                    col++;
                }
                if(count==10) {
                    returnboard[row][col] = 0;
                    while(true) {
                    if(row == -1){
                        return null;
                    }
                        if(col==0) {
                            row--;
                            col=9;
                        }else{
                            col--;
                            if(copyboard[row][col]==0) {
                                if(returnboard[row][col]==9) {
                                    returnboard[row][col] = 0;
                                    continue;
                                }
                                count = returnboard[row][col]+1;
                                break;
                            }
                        }
                    }
                }
                if(col==9) {
                    row++;
                    col=0;
                    count=1;
                }
                if(row==9) {
                    break;
                }
            }

            int[][] addboard = new int[9][9];

            for(int j=0; j<9; j++){
                for(int k=0; k<9; k++){
                    addboard[j][k] = returnboard[j][k];
                }
            }
            list.add(addboard);
            row = 8;
            col = 8;
            while(true){
                if(copyboard[row][col]==0){
                    break;
                }
                col--;
                if(col==-1){
                    col=8;
                    row--;
                }
            }
            count = returnboard[row][col] + 1;
            if(count==10) {
                returnboard[row][col] = 0;
                while(true) {
                    if(col==0) {
                        row--;
                        col=9;
                    }else{
                        col--;
                        if(copyboard[row][col]==0) {
                            if(returnboard[row][col]==9) {
                                returnboard[row][col] = 0;
                                continue;
                            }
                            count = returnboard[row][col]+1;
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * ナンプレに答えがあるかどうかチェックします。
     * 答えが存在した場合{@code true}、答えがない場合{@code false}を返します。
     * もし{@code null}を渡した場合は{@code false}を返します。
     * もしナンプレ内に0未満、または9より大きい値が含まれていた場合{@code false}を返します。
     * @param board ナンプレにみたてた9行9列の{@code int[][]}型。
     * @return ナンプレに答えが存在した場合{@code true}、答えがない場合{@code false}を返す。
     */
    public static boolean anserCheck(int[][] board){
        if(Objects.isNull(board))return false;
        if(!judgeAll(board))return false;
        int row = 0;
        int col = 0;
        int count = 1;
        int[][] copyboard = new int[9][9];
        int[][] anserboard = new int[9][9];
        int boardrow = board.length>9?9:board.length;
        int[] boardcol = new int[9];

        for(int i=0; i<boardrow; i++){
            boardcol[i] = board[i].length;
        }
        for(int i=0; i<boardrow; i++){
            for(int j=0; j<boardcol[i]; j++){
                copyboard[i][j] = board[i][j];
                anserboard[i][j] = board[i][j];
            }
        }
        if(!check(copyboard))return false;
        while(true) {
            if(copyboard[row][col] == 0) {
                anserboard[row][col] = count;
                if(!judge(anserboard,row,col)) {
                    count++;
                }else {
                    col++;
                    count = 1;
                }
            }else {
                col++;
            }
            if(count==10) {
                anserboard[row][col] = 0;
                while(true) {
                if(row == -1){
                    return false;
                }
                    if(col==0) {
                        row--;
                        col=9;
                    }else{
                        col--;
                        if(copyboard[row][col]==0) {
                            if(anserboard[row][col]==9) {
                                anserboard[row][col] = 0;
                                continue;
                            }
                            count = anserboard[row][col]+1;
                            break;
                        }
                    }
                }
            }
            if(col==9) {
                row++;
                col=0;
                count=1;
            }
            if(row==9) {
                return true;
            }
        }
    }

    /**
     * ナンプレに答えが2つ以上あるかチェックします。
     * 答えが2つ以上ある場合は{@code true}、それ以外は{@code false}を返します。
     * もし{@code null}を渡した場合は{@code false}を返します。
     * もしナンプレ内に0未満、または9より大きい値が含まれていた場合{@code false}を返します。
     * @param board ナンプレにみたてた9行9列のint型二次元配列。
     * @return ナンプレに答えが2つ以上ある場合は{@code true}、それ以外は{@code false}を返す。
     */
    public static boolean anotherAnserCheck(int[][] board){
        if(Objects.isNull(board))return false;
        if(!judgeAll(board))return false;
        int row = 0;
        int col = 0;
        int count = 1;
        int[][] copyboard = new int[9][9];
        int[][] anserboard = new int[9][9];
        int boardrow = board.length>9?9:board.length;
        int[] boardcol = new int[9];

        for(int i=0; i<boardrow; i++){
            boardcol[i] = board[i].length;
        }
        for(int i=0; i<boardrow; i++){
            for(int j=0; j<boardcol[i]; j++){
                copyboard[i][j] = board[i][j];
                anserboard[i][j] = board[i][j];
            }
        }

        if(!check(copyboard))return false;
        while(true) {
            if(copyboard[row][col] == 0) {
                anserboard[row][col] = count;
                if(!judge(anserboard,row,col)) {
                    count++;
                }else {
                    col++;
                    count = 1;
                }
            }else {
                col++;
            }
            if(count==10) {
                anserboard[row][col] = 0;
                while(true) {
                if(row == -1){
                    return false;
                }
                    if(col==0) {
                        row--;
                        col=9;
                    }else{
                        col--;
                        if(copyboard[row][col]==0) {
                            if(anserboard[row][col]==9) {
                                anserboard[row][col] = 0;
                                continue;
                            }
                            count = anserboard[row][col]+1;
                            break;
                        }
                    }
                }
            }
            if(col==9) {
                row++;
                col=0;
                count=1;
            }
            if(row==9) {
                row = 8;
                col = 8;
                while(true){
                    if(copyboard[row][col]==0){
                        break;
                    }
                    col--;
                    if(col==-1){
                        col=8;
                        row--;
                    }
                }
                count = anserboard[row][col] + 1;
                if(count==10) {
                    anserboard[row][col] = 0;
                    while(true) {
                        if(col==0) {
                            row--;
                            col=9;
                        }else{
                            col--;
                            if(copyboard[row][col]==0) {
                                if(anserboard[row][col]==9) {
                                    anserboard[row][col] = 0;
                                    continue;
                                }
                                count = anserboard[row][col]+1;
                                break;
                            }
                        }
                    }
                }
                
                while(true) {
                    if(copyboard[row][col] == 0) {
                        anserboard[row][col] = count;
                        if(!judge(anserboard,row,col)) {
                            count++;
                        }else {
                            col++;
                            count = 1;
                        }
                    }else {
                        col++;
                    }
                    if(count==10) {
                        anserboard[row][col] = 0;
                        while(true) {
                        if(row == -1){
                            return false;
                        }
                            if(col==0) {
                                row--;
                                col=9;
                            }else{
                                col--;
                                if(copyboard[row][col]==0) {
                                    if(anserboard[row][col]==9) {
                                        anserboard[row][col] = 0;
                                        continue;
                                    }
                                    count = anserboard[row][col]+1;
                                    break;
                                }
                            }
                        }
                    }
                    if(col==9) {
                        row++;
                        col=0;
                        count=1;
                    }
                    if(row==9) {
                        return true;
                    }
                }
            }
        }
    }

    /**
     * ナンプレが問題として成立するかチェックします。
     * 厳密にいうと、答えが1つのみの場合{@code true}を返します。
     * もし{@code null}を渡した場合は{@code false}を返します。
     * もしナンプレ内に0未満、または9より大きい値が含まれていた場合{@code false}を返します。
     * @param board ナンプレにみたてた9行9列のint型二次元配列。
     * @return ナンプレの答えが1つのみの場合{@code true}、それ以外は{@code false}を返す。
     */
    public static boolean problemCheck(int[][] board){
        if(Objects.isNull(board))return false;
        if(anserCheck(board)&&!anotherAnserCheck(board)){
            return true;
        }
        return false;
    }

    /**
     * 指定された位置の要素がナンプレの制約に違反していないかをチェックします。
     * 要素が属している、縦、横、3x3のブロック、に要素とかぶっている数字がある場合{@code false}を返します。
     * ない場合{@code true}を返します。
     * 位置の指定方法は第2引数に行番号、第3引数に列番号を渡してください。行、列ともに0から8の値を指定してください
     * もし行番号、列番号に0未満の値、もしくは8より大きい値を指定した場合は{@code false}を返します。
     * もし指定された位置の要素が0の場合{@code true}を返します。
     * もし指定された位置の要素が0未満、または9より大きい値の場合{@code false}を返します。
     * もし第1引数に{@code null}を渡した場合はfalseを返します。
     * 
     * @param board ナンプレにみたてた9行9列の{@code int[][]}型。
     * @param row 指定する行番号
     * @param col 指定する列番号
     * @return 指定された位置がナンプレの制約に違反していない、もしくは0の場合{@code true}を返す。
     */
    public static boolean judge(int[][] board,int row, int col) {
        if(Objects.isNull(board))return false;
        if(row<0||row>8||col<0||col>8)return false;
        int match = 0;
        int[][] copyboard = new int[9][9];
        int boardrow = board.length>9?9:board.length;
        int[] boardcol = new int[9];

        for(int i=0; i<boardrow; i++){
            boardcol[i] = board[i].length;
        }
        for(int i=0; i<boardrow; i++){
            for(int j=0; j<boardcol[i]; j++){
                copyboard[i][j] = board[i][j];
            }
        }
        if(copyboard[row][col]>9||copyboard[row][col]<0)return false;
        if(copyboard[row][col] == 0){
            return true;
        }
        
        for(int i=0;i<9;i++) {
            if(copyboard[row][col] == copyboard[row][i]) {
                match++;
            }
        }
        if(match>=2) {
            return false;
        }else {
            match = 0;
            for(int i=0;i<9;i++) {
                if(copyboard[row][col] == copyboard[i][col]) {
                    match++;
                }
            }
            if(match>=2) {
                return false;
            }else {
                if(copyboard[row][col] == copyboard[KUMI[0][row]][KUMI[0][col]]||copyboard[row][col] == copyboard[KUMI[1][row]][KUMI[1][col]]||copyboard[row][col] == copyboard[KUMI[0][row]][KUMI[1][col]]||copyboard[row][col] == copyboard[KUMI[1][row]][KUMI[0][col]]) {
                    return false;
                }else {
                    return true;
                }
            }
        }
    }

    /**
     * すべての要素がナンプレの制約に違反していないかをチェックします。
     * 要素が属している、縦、横、3x3のブロック、に要素とかぶっている数字がある場合{@code false}を返します。
     * ない場合{@code true}を返します。
     * もし{@code null}を渡した場合は{@code false}を返します。
     * もしナンプレ内に0未満、または9より大きい値が含まれていた場合{@code false}を返します。
     * @param board ナンプレにみたてた9行9列の{@code int[][]}型。
     * @return すべての要素がナンプレの制約に違反していない、または0の場合{@code true}を返す。
     */
    public static boolean judgeAll(int[][] board){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(!judge(board, i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean check(int[][] board){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j]>9||board[i][j]<0){
                    return false;
                }
            }
        }
        return true;
    }
}