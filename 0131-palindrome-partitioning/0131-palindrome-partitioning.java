class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s,0,new ArrayList<>(), result);
        return result;
    }
    private void backtrack(String s, int start, List<String> path, List<List<String>> result){
        if(start == s.length()){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int end = start + 1; end<=s.length(); end++){
            String prefix = s.substring(start, end);
            if(isPalidrome(prefix)){
                path.add(prefix);
                backtrack(s,end,path,result);
                path.remove(path.size()-1);
            }
        }
    }
    private boolean isPalidrome(String str){
        int l = 0, r = str.length()-1;
        while(l < r){
            if(str.charAt(l++) != str.charAt(r--)){
                return false;
            }
        }
        return true;
    }
}