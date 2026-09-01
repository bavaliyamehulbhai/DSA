class Solution {
    static class State {
        int r, c, energy, mask;
        State(int r, int c, int energy, int mask) {
            this.r = r; this.c = c; this.energy = energy; this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr=-1, sc=-1;
        int litterCount=0;
        int[][] litterId=new int[m][n];
        for(int[] row:litterId) Arrays.fill(row,-1);

        // Find start and litter
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                char ch=classroom[i].charAt(j);
                if(ch=='S'){ sr=i; sc=j; }
                if(ch=='L'){ litterId[i][j]=litterCount++; }
            }
        }

        int fullMask=(1<<litterCount)-1;
        boolean[][][][] visited=new boolean[m][n][energy+1][1<<litterCount];
        Queue<State> q=new ArrayDeque<>();
        q.offer(new State(sr,sc,energy,0));
        visited[sr][sc][energy][0]=true;

        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        int moves=0;

        while(!q.isEmpty()){
            int size=q.size();
            while(size-- >0){
                State cur=q.poll();
                if(cur.mask==fullMask) return moves;

                for(int[] d:dirs){
                    int nr=cur.r+d[0], nc=cur.c+d[1];
                    if(nr<0||nr>=m||nc<0||nc>=n) continue;
                    char cell=classroom[nr].charAt(nc);
                    if(cell=='X') continue;
                    if(cur.energy==0) continue;

                    int ne=cur.energy-1;
                    int nmask=cur.mask;
                    if(cell=='L'){
                        int id=litterId[nr][nc];
                        if(id!=-1) nmask|=(1<<id);
                    }
                    if(cell=='R') ne=energy;

                    if(!visited[nr][nc][ne][nmask]){
                        visited[nr][nc][ne][nmask]=true;
                        q.offer(new State(nr,nc,ne,nmask));
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
