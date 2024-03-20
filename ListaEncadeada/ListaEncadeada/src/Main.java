import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ArrayList<String> meuArrayList = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();

        list.add(5);
        list.add(8);
        list.add(11);
        list.add(22);
        list.add(13);
        list.insert(40);
        list.insert(2,15);

        list.insertOrder(7);
        list.insertOrder(9);
        list.insertOrder(30);
        list.insertOrder(90);

        System.out.println(list);
        list.removeByIndex(2);


        System.out.println(list);
    }
}

//Exceptions
class EmptyListException extends RuntimeException {
    public EmptyListException(String errorMessage){
        super(errorMessage);
    }
}

// TAD - List
interface List<E>{
    int size();
    void add(E value);
    void insert(E value);
    void insert(int index, E value) throws IndexOutOfBoundsException;
    E removeLast() throws EmptyListException ;
    E removeFirst() throws EmptyListException;
    E removeByIndex(int index) throws IndexOutOfBoundsException,EmptyListException;
    boolean isEmpty();
    E get(int index) throws IndexOutOfBoundsException;
    void set(int index, E value) throws IndexOutOfBoundsException;
}



class LinkedList<E> implements List<E> {

    private class Node {
        E value;
        Node next;

        public Node(E value) {
            this.value = value;
            // next = null;
        }
    }


    private int size;
    private Node head;
    private Node tail;

    // Construtores vazios e com um valor
    public LinkedList() {
    }

    public LinkedList(E value) {
        add(value);
    }

    // Adiciona um elemento ao final da lista
    @Override
    public void add(E value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    // Obtém o valor do nó em um índice específico
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyListException("Linked List is Empty!");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index " + index + ". Availabe indexes are [0 - " + (size - 1) + "]");
        }

        return getNode(index).value;
    }

    // Insere um elemento no início da lista
    @Override
    public void insert(E value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        size++;
    }

    // Obtém o nó em um índice específico
    private Node getNode(int index) {
        Node auxNode = head;
        for (int i = 0; i < index; i++) {
            auxNode = auxNode.next;
        }
        return auxNode;
    }

    // Insere um elemento em um índice específico
    @Override
    public void insert(int index, E value) {

        if (index <= 0) {
            insert(value);
        } else if (index >= size) {
            add(value);
        } else {
            Node newNode = new Node(value);
            Node auxNode = getNode(index - 1);
            newNode.next = auxNode.next;
            auxNode.next = newNode;
            size++;

        }

    }

    // Verifica se a lista está vazia
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Remove um elemento por índice
    @Override
    public E removeByIndex(int index) throws IndexOutOfBoundsException, EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Linked List is Empty!");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index " + index + ". Availabe indexes are [0 - " + (size - 1) + "]");
        }
        E value = null;
        if (index == 0) {
            value = removeFirst();
        } else if (index == size - 1) {
            value = removeLast();
        } else {
            Node auxNode1 = getNode(index - 1);
            Node auxNode2 = auxNode1.next;
            auxNode1.next = auxNode2.next;
            // auxNode2.next = null;
            value = auxNode2.value;
            size--;
        }

        return value;
    }

    // Remove o primeiro elemento da lista
    @Override
    public E removeFirst() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Linked List is Empty!");
        }
        Node auxNode = head;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            auxNode.next = null;
        }
        size--;
        return auxNode.value;
    }

    // Remove o último elemento da lista
    @Override
    public E removeLast() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Linked List is Empty!");
        }
        E value = tail.value;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node auxNode = getNode(size - 2);
            tail = auxNode;
            auxNode.next = null;
        }
        size--;
        return value;
    }

    // Define o valor de um elemento em um índice específico
    @Override
    public void set(int index, E value) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyListException("Linked List is Empty!");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index " + index + ". Availabe indexes are [0 - " + (size - 1) + "]");
        }

        getNode(index).value = value;


    }

    // Retorna o tamanho da lista
    @Override
    public int size() {
        return size;
    }

    // Converte a lista para uma representação de string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node auxNode = head;
        while (auxNode != null) {
            sb.append(auxNode.value);
            if (auxNode.next != null) {
                sb.append(", ");
            }
            auxNode = auxNode.next;
        }
        return sb.append("]").toString();
    }

    // Método para inserir uma lista ordenada dentro da lista atual
    public void insertOrder(LinkedList<E> orderedList) {
        Node current = orderedList.head;
        while (current != null) {
            insertOrder(current.value);
            current = current.next;
        }
    }
    // Método para inserir um elemento de forma ordenada
    public void insertOrder(E value) {
        insert(value);
    }
}


