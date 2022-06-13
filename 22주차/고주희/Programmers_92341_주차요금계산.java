package _202206;

import java.util.*;

class Solution {
    static class Parking {
		int incomingTime;
		int totalTime;
        boolean isParking;
        
		public Parking(String time) {
            this.incomingTime = calTime(time);
            this.totalTime = 0;
            this.isParking = true;
		}
		
        public int calTime(String time) {
            String[] r = time.split(":");
            int tmp = Integer.parseInt(r[0]) * 60 + Integer.parseInt(r[1]);
            return tmp;
        }
        
        public void calTotalTime(String time) {
            int tmp = calTime(time);
            this.totalTime += (tmp - this.incomingTime); 
            this.incomingTime = 0;
            this.isParking = false;
        }
        
        public void setIncomingTime(String time) {
            int tmp = calTime(time);
            this.incomingTime = tmp;
            this.isParking = true;
        }
        
	}
    
    public int[] solution(int[] fees, String[] records) {
        
        Map<String, Parking> map = new HashMap<>();
        
        for(String record : records) {
            String[] re = record.split(" ");
            System.out.println(Arrays.toString(re));
            if(map.containsKey(re[1])) {
                if(re[2].equals("OUT")) {
                    Parking tmp = map.get(re[1]);
                    tmp.calTotalTime(re[0]);
                    map.put(re[1], tmp);
                } else {
                    Parking tmp = map.get(re[1]);
                    tmp.setIncomingTime(re[0]);
                    map.put(re[1], tmp);
                }
            } else {
                String[] t = re[0].split(":");
                if(re[2].equals("IN")){
                    map.put(re[1], new Parking(re[0]));
                }
                
            }
        }
        Set<String> set = map.keySet();
        List<String> list = new ArrayList(set);
        int[] answer = new int[list.size()];
        int t = 0;
        Collections.sort(list);
        System.out.println(list);
        for(String str : list) {
            Parking p = map.get(str);
            int fee = 0;
            if(p.isParking) {
                p.calTotalTime("23:59");
            }
            if(p.totalTime <= fees[0]) {
                fee = fees[1];
            } else {
                int restTime = p.totalTime - fees[0];
                int moreFee = restTime/fees[2];
                if(restTime%fees[2] > 0) moreFee+=1; 
                fee = fees[1] + moreFee * fees[3];
            }
            answer[t++] = fee;
        }
        return answer;
    }
}
