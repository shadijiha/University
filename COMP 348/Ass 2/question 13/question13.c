#include <stdio.h>
#include <stdlib.h>

typedef enum { ATOM, LIST } eltype;
typedef char atom;
struct _listnode;
typedef struct {
    eltype type;
    union {
        atom a;
        struct _listnode* l;
    };
} element;
typedef struct _listnode {
    element el;
    struct _listnode* next;
} * list;
const element NIL = {.type = LIST, .l = NULL};

element aasel(atom a);
void insert(list* root, element item);

list toList(atom a) {
    list result = (list)malloc(sizeof(struct _listnode));
    result->el = aasel(a);
    result->next = NULL;

    return result;
}

/**
 * Returns an element whose content is set to atom a
 */
element aasel(atom a) {
    element result;
    result.type = ATOM;
    result.a = a;
    return result;
}

/**
 * AKA list as element, returns an element whose content is set to the list,
 * pointed by l.
 */
element lasel(list l) {
    element result;
    result.type = LIST;
    result.l = l;
    return result;
}

/**
 * that creates a new list whose car and cdr are the element e and the list l.
 * While the memory for the newly created list is to be allocated dynamically.
 */
list cons(element e, list l) {
    list first_ele = (list)malloc(sizeof(struct _listnode));
    first_ele->el = e;
    first_ele->next = l;
    return first_ele;
}

list append(list l1, list l2) {
    list newList = NULL;

    list it = l1;
    while (it != NULL) {
        insert(&newList, it->el);
        it = it->next;
    }

    list it2 = l2;
    while (it2 != NULL) {
        insert(&newList, it2->el);
        it2 = it2->next;
    }

    return newList;
}

element car(element e) {
    if (e.type != LIST) {
        return NIL;
    }

    return e.l->el;
}

list cdr(element e) {
    if (e.type != LIST || e.l == NULL) {
        return NULL;
    }

    return e.l->next;
}

list cddr(element e) {
    return cdr(lasel(cdr(e)));
}

void freeList(list l) {
    list it = l;

    while (it != NULL) {
        if (it->el.type == LIST) {
            freeList(it->el.l);
        }

        list temp = it->next;
        free((void*)it);
        it = temp;
    }
}

// ***************** Debug functions (to be removed) *******************
void insert(list* root, element item) {
    list temp = (list)malloc(sizeof(struct _listnode));
    list ptr;
    temp->el = item;
    temp->next = NULL;

    if (*root == NULL)
        *root = temp;
    else {
        ptr = *root;
        while (ptr->next != NULL)
            ptr = ptr->next;
        ptr->next = temp;
    }
}

list listFromArray(char* array, int n) {
    list head = NULL;
    for (int i = 0; i < n; i++) {
        element e = aasel(array[i]);
        insert(&head, e);
    }

    return head;
}

void print(element e) {
    if (e.type == LIST && e.l == NULL) {
        printf(" NIL ");
        return;
    }

    if (e.type == ATOM) {
        printf(" %c ", e.a);
    } else {
        list current = e.l;
        printf(" (");
        while (current != NULL) {
            print(current->el);
            current = current->next;
        }
        printf(") ");
    }
}

int main() {
    printf("\n**************** Question 13 B) ****************\n\n");

    list inside = toList('b');
    inside->next = toList('c');

    list fullList = toList('a');
    list insideHolder = (list)malloc(sizeof(struct _listnode));
    insideHolder->el.type = LIST;
    insideHolder->el.l = inside;
    fullList->next = insideHolder;

    insideHolder->next = toList('d');
    insideHolder->next->next = toList('e');

    print(lasel(fullList));

    printf("\n**************** Question 13 C) ****************\n\n");
    element car_fulllist = car(lasel(fullList));
    printf("Car: ");
    print(car_fulllist);

    printf("\nCdr: ");
    print(lasel(cdr(lasel(fullList))));

    printf("\nCar of car: ");
    print(car(car_fulllist));

    freeList(fullList);

    printf("\n\n");

    return 0;
}