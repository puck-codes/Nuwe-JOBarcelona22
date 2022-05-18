package tokyo.boblennon.bcnjob22.domain;

import java.util.UUID;

import tokyo.boblennon.bcnjob22.core.ExistsByField;
import tokyo.boblennon.bcnjob22.core.FindById;

public interface RoleReadRepository extends FindById<Role, UUID>,ExistsByField {}
