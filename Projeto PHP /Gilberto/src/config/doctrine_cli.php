<?php

require 'vendor/autoload.php';

$path = array('Path/To/Entity');
$devMode = true;

$config = Setup::createAnnotationMetadataConfiguration($path, $devMode);

$connectionOptions = array(
    'driver'   => '',
    'host'     => '',
    'dbname'   => '',
    'user'     => '',
    'password' => '',
);

$em = \Doctrine\ORM\EntityManager::create($connectionOptions, $config);

$helpers = new Symfony\Component\Console\Helper\HelperSet(array(
    'db' => new \Doctrine\DBAL\Tools\Console\Helper\ConnectionHelper($em->getConnection()),
    'em' => new \Doctrine\ORM\Tools\Console\Helper\EntityManagerHelper($em)
));