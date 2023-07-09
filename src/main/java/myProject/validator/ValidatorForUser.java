package myProject.validator;

public interface ValidatorForUser<E,R> {
    R isValidUser(E e);
}
