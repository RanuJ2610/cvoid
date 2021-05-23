package com.cvoid;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class DownloadReports {
    public static void main(String[] args) throws GitAPIException {
        Git.cloneRepository().setURI("https://github.com/CSSEGISandData/COVID-19.git").call();
    }
}
