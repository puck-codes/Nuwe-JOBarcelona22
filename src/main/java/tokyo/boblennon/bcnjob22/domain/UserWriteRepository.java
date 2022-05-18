package tokyo.boblennon.bcnjob22.domain;

public interface UserWriteRepository {
    public User add(User user);
    public User update(User user);
    public void delete(User user);

    public void addRoleToUser(String userName, String roleName);
}
