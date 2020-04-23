#!/usr/bin/env perl

use strict;
use warnings;
use v5.10;

use Mojo::UserAgent;

my $ua = Mojo::UserAgent->new;

my $username = 'anton.fedotov1';
my $password = 'qqwe123';

say "Processing request to create user with username: $username, password: $password";
my $tx = $ua->post('localhost:8080/api/user' => json => { username => $username, password => $password });
say 'Response is: ' . $tx->res->body;

my @files = (
    {
        title       => 'some title',
        content     => 'some content',
        queueNumber => '1',
    },
    {
        title       => 'c++ title',
        content     => 'c++ content',
        queueNumber => '2',
    },
    {
        title       => 'javascript title',
        content     => 'javascript content',
        queueNumber => '3',
    },
);

my %snippet = (
    title    => 'new snippetqweqe',
    isHide   => 'false',
    fileList => \@files,
    user     => { username => $username },
);

say "Processing request to create new snippet by $username...";
$tx = $ua->post('localhost:8080/api/snippet' => json => \%snippet);
say 'Response is: ' . $tx->res->body;
