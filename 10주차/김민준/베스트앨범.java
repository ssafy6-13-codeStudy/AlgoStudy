import java.util.*;

class Solution {
    static class Song implements Comparable<Song>{
        int idx;
        String genre;
        int playcnt;
        
        public Song(int idx, String genre, int playcnt){
            this.idx = idx;
            this.genre = genre;
            this.playcnt = playcnt;
        }
        
        @Override
        public int compareTo(Song o){
            if(this.playcnt == o.playcnt){
                return this.idx - o.idx;
            }
            return o.playcnt - this.playcnt;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < plays.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
        }
        
        List<String> glist = new LinkedList<>();
        while(!map.isEmpty()){
            int max = Integer.MIN_VALUE;
            String genre = "";
            for(String key : map.keySet()){
                if(map.get(key) > max){
                    max = map.get(key);
                    genre = key;
                }
            }
            glist.add(genre);
            map.remove(genre);
        }
        
        List<Song> list = new ArrayList<>();
        for(String genre : glist){
            List<Song> temp = new ArrayList<>();
            for(int i = 0; i < genres.length; i++){
                if(genres[i].equals(genre)){
                    temp.add(new Song(i, genre, plays[i]));
                }
            }        
            Collections.sort(temp);
            
            list.add(temp.get(0)); 
            if(temp.size() >= 2){
                list.add(temp.get(1));
            }
        }
                
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i).idx;
        }
        
        return answer;
    }
}