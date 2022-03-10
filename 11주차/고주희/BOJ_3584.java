import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 시간 : 216ms
// 메모리 : 23556KB
public class Main {

	static class Tree{
		int index;
		int depth;
		Tree parent;
		
		public Tree(int index, int depth) {
			super();
			this.index = index;
			this.depth = depth;
		}

		public void addParent(Tree parent) {
			this.parent = parent;
			this.depth = parent.depth+1;
			
		}

	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			Tree[] trees = new Tree[N+1];
			for (int i = 1; i < N+1; i++) {
				trees[i] = new Tree(i, 1);
			}
			
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				trees[child].addParent(trees[parent]);
			}
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			List<Tree> px = new ArrayList<>();
			List<Tree> py = new ArrayList<>();
			
			px.add(trees[x]);
			py.add(trees[y]);
			
			while(true) {
				if(trees[x].parent==null) break;
				px.add(trees[x].parent);
				
				x = trees[x].parent.index;
			}
			while(true) {
				if(trees[y].parent==null) break;
				py.add(trees[y].parent);
				
				y = trees[y].parent.index;
				
			}
			Tree answer = null;
			int depth = Integer.MIN_VALUE;
			for (Tree tree : py) {
				for (Tree tree2 : px) {
					if(tree.index==tree2.index) {
//						System.out.println(tree.index);
						if(depth<tree.depth) {
							answer = tree;
							depth = tree.depth;
						}
					}
				}
			}
			System.out.println(answer.index);
		}
	}

}
