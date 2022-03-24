class Solution {
    public int solution(String s) {
        int len = s.length();
        int min = len;
        char[] c = s.toCharArray();
        int count = 1; // 단어 자를 길이
        
        while(count<=len) {
        	int start = count; // pattern의 첫 index
        	int end = start+count; // pattern의 마지막 index
        	int cnt = 1;
        	int answer = 0; // 압축했을 때 길이
        	while(start<len) { // 마지막 index가 문장 길이를 넘어가면 끝
                if(end>len) {
                    break;
                }
        		boolean flag = true; // pattern과 동일한가?
        		 // pattern과 같은 것 개수 -> pattern 포함이라 1로 시작
        		for (int i = start; i < end; i++) { // pattern 길이 동안
					if(c[i] != c[i-count]) { // i번째와 i-count번째가 하나라도 다르다면
						flag = false; // flag에 false 저장
						break; // for문 나가기
					}
				}
        		if(flag) { // pattern과 동일한 게 있다
        			cnt++; // 개수++
        		}
        		else { // pattern과 동일하지 않다
        			if(cnt!=1) {
        				if(cnt==1000) answer+=4;
        				else if(cnt>=100) answer+=3;
        				else if(cnt>=10) answer+=2;
        				else answer++; // 지금까지 센 개수 더하기
        			}
        			answer+=count; // 자른 단어 길이 == count
        			cnt = 1;
        		}
        		start = end; // start 위치 옮기기
    			end = start+count; // end 위치 옮기기
        	}
            if(cnt!=1) {
    				if(cnt==1000) answer+=4;
        				else if(cnt>=100) answer+=3;
        				else if(cnt>=10) answer+=2;
        				else answer++; // 지금까지 센 개수 더하기
    			}
    			answer+=count; // 자른 단어 길이 == count
			if(start-count<len) {
			    answer+=len-end+count;
			}
        	count++;
        	min = Math.min(min, answer);
        }
        
        return min;
    }
}
