package tree;

import java.util.Scanner;

/**
 * @author neo82
 */
public class RangeSum {

    public static void main(String[] args) {
        long [] input = {1,2,3,4,5};

        SegmentTree segmentTree = new SegmentTree();
        segmentTree.init(input, 0, input.length - 1);

        segmentTree.update(0, 3);
        System.out.println(segmentTree.query(0, 2));
    }

    static class SegmentTree {
        int leftIndex;
        int rightIndex;
        long sum;
        SegmentTree left;
        SegmentTree right;

        // O(n)
        long init(long[] input, int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;

            if (leftIndex == rightIndex) {
                return this.sum = input[leftIndex];
            }

            int mid = leftIndex + (rightIndex - leftIndex) / 2;

            if (leftIndex <= mid) {
                this.left = new SegmentTree();
                this.sum += left.init(input, leftIndex, mid);
            }

            if (mid + 1 <= rightIndex) {
                this.right = new SegmentTree();
                this.sum += right.init(input, mid + 1, rightIndex);
            }

            return this.sum;
        }

        // O(log n)
        long update(int i, long val) {
            // 범위를 벗어난 경우에도 상위 노드에서는 양쪽 구간의 합을 처리해야 하므로 sum 을 return 한다.
            if (i < this.leftIndex || this.rightIndex < i) {
                return this.sum;
            }

            if (this.leftIndex == this.rightIndex) {
                return this.sum = val;
            }

            long sum = 0; // must be new value

            if (this.left != null) {
                sum += this.left.update(i, val);
            }

            if (this.right != null) {
                sum += this.right.update(i, val);
            }

            return this.sum = sum;
        }

        // O(log n)
        long query(int leftIndex, int rightIndex) {
            // 범위를 벗어난 경우, leftIndex ~ rightIndex 구간의 query 에 해당값은 절대 포함되서는 안되므로 0을 리턴한다.
            if (rightIndex < this.leftIndex || leftIndex > this.rightIndex) {
                return 0;
            }

            if (leftIndex <= this.leftIndex && this.rightIndex <= rightIndex) {
                return this.sum;
            }

            int mid = this.leftIndex + (this.rightIndex - this.leftIndex) / 2;

            long sum = 0;

            if (leftIndex <= mid) {
                sum += this.left.query(leftIndex, Math.min(mid, rightIndex));
            }

            if (rightIndex > mid) {
                sum += this.right.query(Math.max(leftIndex, mid + 1), rightIndex);
            }

            return sum;
        }
    }

}
