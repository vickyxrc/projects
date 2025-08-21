public class exam3 {
    //Huffman
    /*: pre: bis != null and is positioned before the first bit of the VCF header data,
        after the VCF format constant itself. (MAGIN_NUM and VCF already read in.)
        Assume the header itself is correct with no errors.
        post: returns true if all the codes in the header are present and correct
        in this HuffmanCodeTree and all values in TreeNodes associated with said
        codes are correct. Returns false if there is any error in the tree
        based on the header bis is connected to. */
private boolean verifyTree(BitInputStream bis) 
    private boolean verifyTree(BitInputStream bis) {
        int numCodes = bis.readBits(8);
        for (int i = 0; i < numCodes; i++) {
            int ogValue = bis.readBits(9);
            int newLength = bis.readBits(8);
            int huffManBitNum = 0;
            TreeNode temp = root;
            while (temp != null && huffManBitNum < newLength) {
                if (temp.value != -1) {
                    return false;
                }
                int nextBit = bis.readBits(1);
                huffManBitNum++;
                temp = nextBit == 0 ? temp.left : temp.right;
            }
            if (temp == null) {
                //fell off tree prematurely
                return false;
            }
            if (temp.value != ogValue) {
                //unmactched value
                return false;.
            }
            if (temp.left == null || temp.right == null) {
                //Didn't reach leaf
                return false;
            }
        }
        return true;
    }

    //graphs
    /* pre: requiredHubs > 0, factor > 1.0. post: per the problem description. */
    public boolean hasRequiredHubs(int requiredHubs, double factor) {
        int numHubs = 0;
        //find average num degrees
        int sumDeg = 0;
        for (Vertex v: vertices.values()) {
            sumDeg += v.adjacent.size();
        }
        double avgDeg = (double) sumDeg / vertices.size();
        double hubFactor = factor * avgDeg;
        for (Vertex v: vertices.values()) {
            if (v.adjacent.size() >= hubFactor) {
                numHubs++;
            }
        }
        return numHubs >= requiredHubs;
    }

    //tree
   /* pre: per the problem description
    post: neither valueRange or depthRange are altered as a result of
    this method and per the problem description. */
    public int inRangeSum(int[] valueRange, int[] depthRange) {
        int sum = 0;
        return rangeSumHelper(valueRange, depthRange, 0, root);
        
    }
    private int rangeSumHelper(int[] valueRange, int[] depthRange, int depth, intNode node) {
        if (node == null || depth > depthRange[1]) {
            return 0; //We don't need to know anything beyond the depthRange
        }
        if (node.val >= valueRange[0] && node.val <= valueRange[1] && depth >= depthRange[0]) {
            return node.val;
        }
        int sum = 0;
        sum += rangeSumHelper(valueRange, depthRange, depth + 1, node.left);
        sum += rangeSumHelper(valueRange, depthRange, depth + 1, node.right);
        return sum;
    }

    //graph
    // public method that kicks off the recursive helper. Do not alter
    /* start != null, this Graph contains a Vertex with the given
    name. n >= 2. */
    public ArrayList<String> getNHopNeighbors(String start, int n) {
        ArrayList<String> result = new ArrayList<>();
        clearAll();
        Vertex startVertex = vertices.get(start);
        hopHelp(result, startVertex, n, 0);
        return result;
   }
   private void hopHelp(ArrayList<String> result, Vertex currentVertex, int goalEdges, int currentEdges) {
        if (currentVertex.scratch == 0) {
            if (currentEdges == goalEdges) {
                if (!result.contains(currentVertex.name)) { //currentEdgs == goalEdges
                    result.add(currentVertex.name);
                }
            }
            else { //only evaluate within goalEdges
                currentEdges++;
                for (Edge e : adjacent) {
                    Vertex neighbor = e.dest;                    
                    hopHelp(result, neighbor, goalEdges, currentEdges);
                }
                
            }
            currentVertex.scratch == 1;
           
        }
        
   }
   
   //tree
   /* pre: size() >= 2, tgt != null */
    public int remove(E tgt) {
        return helper(tgt, root);
   }
   // remove leaves with tgt and return the number of nodes removed
    private int helper(E tgt, Node n) {
        if (n.children == null) {
            if (n.equals(tgt)) {
            
            }
            return 0;
        }
        numRemoved = 0;
        for (Node child : n.children) {
            numRemoved += helper(tgt, child);
        }
        return numRemoved;
    }

    //tree
    /* pre: tgt != null
    post: return the depth of the deepest node that contains tgt
    or -1 if tgt is not present in this BinTree. This BinTree is
    not altered as a result of this method. */
    public int deepestDepth(E tgt) {
        return deepestHelper(root, tgt, 0);
    }
    private int deepestHelper(BNode<E> node, E tgt, int currentDepth) {
        int deepest = -1;
        if (node != null) {
            
            if (node.data.equals(tgt)) {
                deepest = currentDepth;                

            }
            int leftDepth = deepestHelper(node.left, tgt, currentDepth + 1);
            int rightDepth = deepestHelper(node.right, tgt, currentDepth + 1);
            deepest = Math.max(deepest, Math.max(leftDepth, rightDepth));

        }
        return deepest;
    }
    //huffman
    /* pre: bis != null, bis is positioned at the start of a file properly
    encoded with the specification described. There is at least 1 number in the
    sequence. post: per the problem statement*/
    public static ArrayList<Integer> decodeDeltaEncoding(BitInputStream bis) {
        ArrayList<Integer> encoding = new ArrayList<Integer>();
        int bitsPerDelt = bis.readBits(5);

        int value = bis.readBits(32); //initial value. bis returns int representation of the bits
        encoding.add(value);
        
        int sign = bis.readBits(1); 
        int delta = bis.readBits(bitsPerDelt);
        
        while (sign != -1 && delta != -1) {
            value += sign == 0 ? delta : -delta;
            encoding.add(value);
            sign = bis.readBits(1);
            delta = bis.readBits(bitsPerDelt);
        }
        return encoding;
    }

    //graph
    /* pre: none, post: per the problem description */
    public TreeSet<String> getSourceVertices() {
        //reset all scratch to 0
        for (String v : vertices.keySet()) {
            vertices.get(v).scratch = 0;
        }
        //traverse through all the destinations and set scractch to 1
        for (String v: vertices.keySet()) {
            Vertex vertex = vertices.get(v);
            for (Edge e : vertex.adjacent) {
                e.dest.scratch = 1;
            }
        }
        //check which vertices are unvisited. these are the source vertices
        TreeSet<String> sources = new TreeSet<String>();
        for (String v : vertices.keySet()) {
            if (vertices.get(v).scratch == 0) {
                sources.add(v);
            }
        }
        return sources;
    }

    //Huffman
    /* pre: s != null, s.length() >= 1
    post: return the message encoded by s or null if s is not a valid
    encoding. This MorseCodeTree is not altered by this method call. */
    public String decode(String s) {
        String decoded = "";
        int currChar = 0;
        while (currChar < s.length()) { //stop when we reach the end of the string
            if (s.chatAt(currChat) == '*') {
                return null;
            }
            String morseCode = "";
            while (s.charAt(currChar) != '*' && currChar < s.length()) { //keep adding chars until we get a "*"
                morseCode += s.charAt(currChar);
                currChar++;
            }

            currChar++; //skip the "*" since it's not part of the morse code
            //traverse tree
            MNode node = root;                 
            int morseInd = 0;
            while (node != null && morseInd < morseCode.length()) {
                char symbol = morseCode.charAt(morseInd);
                if (symbol != '.' && symbol != '-') {
                    return null;
                }
                node = symbol == '.' ? node.left : node.right;
                morseInd++;
            }
            if (node == null) {
                return null;
            }
            decoded += node.letter;
        }
        return decoded;
    }

    //graph
    public boolean containsHamiltonianPath() {
        clearAll();
        for (String name : vertices.keySet()) {
            if (helper(name, 0)) {
                currentStartVertex = name;
                return true;
            }
        }
        return false;
    }
    /* Complete the following method. Do not add any other helper methods.
    DO not change the method header. Do not add or remove elements from
    the map named vertices. */
    private boolean helper(String currentVertexName, int verticesInPath) {
        Vertex vertex = vertices.get(currentVertex);
        if (vertex.scratch == 1) { //already visited, path cannot be hamiltonian
            return false;
        }
        if (verticesInPath == vertices.size()) {
            return true;
        }
        vertex.scratch = 1; //mark as visited
        verticesInPath++;
        for (Edge e : vertex.adjacent) {
            Vertex adjacent = edge.dest;
            if (helper(adjacent.name, verticesInPath)) {
                return true;
            }
        }
        vertex.scratch = 0;
        return false;
    }

    //tree
    /* pre: tgt != null, minDepth >= 0
     post: Per the problem description. This BinTree is not altered
    by this method. */
    public int count(E tgt, int minDepth) {
        return countHelper(root, tgt, minDepth, 0);
    }
    private int countHelper(BNode<E> node, E tgt, int minDepth, int currentDepth) {
        if (node == null) {
            return 0;
        }
        int counts = 0;

        if (currentDepth > minDepth && node.left == null && node.right == null && node.data.equals(tgt)) {
            counts++;
        }
        int nextDepth = currentDepth + 1;
        counts += countHelper(BNode.left, tgt, minDepth, nextDepth) + countHelper(BNode.right, tgt, minDepth, nextDepth);
        return counts;
    }







}