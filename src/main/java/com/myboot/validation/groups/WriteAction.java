package com.myboot.validation.groups;

import javax.validation.GroupSequence;

/**
 * @author Nansen
 * @create 2020/4/5 11:28
 */
@GroupSequence({Create.class,Delete.class,Update.class})
public interface WriteAction {
}
