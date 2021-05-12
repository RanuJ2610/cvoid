package com.cvoid;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import java.io.File;

public class Download {
    public static void main(String[] args) throws InvalidRemoteException, TransportException, GitAPIException {
        Git.cloneRepository()
                .setURI("https://github.com/eclipse/jgit.git")
                .setDirectory(new File("/path/to/targetdirectory"))
                .call();
    }
}
