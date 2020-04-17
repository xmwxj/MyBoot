package com.myboot.validation.groups;

import javax.validation.GroupSequence;

/**
 * @author Nansen
 * @create 2020/4/17 13:11
 */
@GroupSequence({Step1.class,Step2.class})
public interface SeqValidator {
}
