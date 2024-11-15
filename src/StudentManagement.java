public class StudentManagement {
    private Node head; // Đầu của danh sách liên kết

    // Constructor
    public StudentManagement() {
        this.head = null;
    }

    // 1. Thêm học sinh vào đầu danh sách
    public void addFirst(Student student) {
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        System.out.println("đã thêm học sinh");
    }

    // 2. Thêm học sinh vào cuối danh sách
    public void addLast(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
            System.out.println("đã thêm học sinh");
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;

    }

    // 3. Xóa học sinh ở đầu danh sách
    public void deleteFirst() {
        if (head == null) {
            System.out.println("Danh sách trống!");
            return;
        }
        head = head.next;
    }

    // 4. Xóa học sinh ở cuối danh sách
    public void deleteLast() {
        if (head == null) {
            System.out.println("Danh sách trống!");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    // 5. Duyệt qua danh sách và in thông tin học sinh
    public void traverse() {
        if (head == null) {
            System.out.println("Danh sách trống!");
            return;
        }
        Node current = head;
        System.out.println("\nDanh sách học sinh:");
        System.out.println(String.format("%-10s %-20s %-5s %-10s", "Mã HS", "Tên học sinh", "Điểm", "Xếp hạng"));
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // 6. Tìm kiếm học sinh theo mã số
    public Student searchById(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // 7. Cập nhật điểm của học sinh theo mã số
    public void updateStudent(String id, double newScore) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id)) {
                current.data = new Student(id, current.data.getName(), newScore);
                System.out.println("Đã cập nhật điểm cho học sinh " + id);
                return;
            }
            current = current.next;
        }
        System.out.println("Không tìm thấy học sinh với mã số: " + id);
    }

    //8. Xóa học sinh theo mã số
    public void deleteById(String id) {
        if (head == null) {
            System.out.println("Danh sách trống!");
            return;
        }
        if (head.data.getId().equalsIgnoreCase(id)) {
            head = head.next;
            System.out.println("Đã xóa học sinh " + id);
            return;
        }
        Node current = head;
        while (current.next != null && !current.next.data.getId().equalsIgnoreCase(id)) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Không tìm thấy học sinh với mã số: " + id);
        } else {
            current.next = current.next.next;
            System.out.println("Đã xóa học sinh " + id);
        }
    }
    // 9. Sắp xếp theo điểm (bubblesort)
    public void bubbleSortByScore() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.data.getScore() > current.next.data.getScore()) {
                    // Hoán đổi dữ liệu giữa hai nút
                    Student temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
        System.out.println("Đã sắp xếp danh sách theo điểm từ thấp đến cao.");
    }

    // 10. Sắp xếp học sinh theo tên (Insertion Sort)
    public void insertionSortByName() {
        if (head == null || head.next == null) return;
        Node sorted = null;

        Node current = head;
        while (current != null) {
            Node next = current.next;

            // Chèn nút vào danh sách đã sắp xếp
            if (sorted == null || sorted.data.getName().compareToIgnoreCase(current.data.getName()) >= 0) {
                current.next = sorted;
                sorted = current;
            } else {
                Node temp = sorted;
                while (temp.next != null && temp.next.data.getName().compareToIgnoreCase(current.data.getName()) < 0) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }
            current = next;
        }
        head = sorted;
        System.out.println("Đã sắp xếp danh sách theo tên từ A-Z.");
    }
}