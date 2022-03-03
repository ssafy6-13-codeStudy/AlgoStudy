import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Genre implements Comparable<Genre>{
		String genre;
		int total;
		List<Play> list;
		
		public Genre(String genre, int total, int index) {
			super();
			this.genre = genre;
			this.total = total;
			this.list = new ArrayList<>();
			this.list.add(new Play(index, total));
		}
		
		public void add(int total, int index) {
			this.total += total;
			this.list.add(new Play(index, total));
		}

		@Override
		public int compareTo(Genre o) {
			return Integer.compare(o.total, this.total);
		}
		
	}
	
	class Play implements Comparable<Play>{
		int index;
		int play;
		public Play(int index, int play) {
			super();
			this.index = index;
			this.play = play;
		}
		@Override
		public int compareTo(Play o) {
			int n = Integer.compare(o.play, this.play);
			if(n==0) n = Integer.compare(this.index, o.index);
			return n;
		}
		
		
		
	}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
          Map<String, Genre> map = new HashMap<>();
        int N = genres.length;
        for (int i = 0; i < N; i++) {
			if(map.containsKey(genres[i])) {
				map.get(genres[i]).add(plays[i], i);
			}else {
				map.put(genres[i], new Genre(genres[i], plays[i], i));
			}
		}
        List<Map.Entry<String, Genre>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        
        int[] answer = {};
        List<Integer> ans = new ArrayList<>();
        for (Entry<String, Genre> entry : entries) {
			List<Play>tmp = entry.getValue().list;
			tmp.sort(null);
			int cnt = Math.min(tmp.size(), 2);
			for (int i = 0; i < cnt; i++) {
				ans.add(tmp.get(i).index);
			}
		}
        int t = ans.size();
        answer = new int[t];
        for (int i = 0; i < t; i++) {
			answer[i] = ans.get(i);
		}
        return answer;
    }
}
