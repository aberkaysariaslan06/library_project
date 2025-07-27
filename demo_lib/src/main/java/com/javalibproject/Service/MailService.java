package com.javalibproject.Service;

import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;

public interface MailService {
    void sendUserUpdatedMail(SystemUser user);
    void sendUserCreatedMail(SystemUser user);
}
