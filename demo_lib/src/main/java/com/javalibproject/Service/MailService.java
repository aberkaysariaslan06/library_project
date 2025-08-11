package com.javalibproject.Service;

import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Repo.user.User.SystemUser;

public interface MailService {
    void sendUserUpdatedMail(SystemUser user);

    void sendUserCreatedMail(SystemUser user);
}
