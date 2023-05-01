---
id: rust
title: Rust Extension
sidebar_label: Rust
---

The following section contains Rust-specific extensions to the build server
protocol.

## Rust Workspace Request
This endpoint returns information in format that can be easily used by rust-intellij to get all information required to resolve the project
- method: `buildTarget/rustWorkspace`
- params: `RustWorkspaceParams`

```ts
export interface RustWorkspaceParams {
  targets: List[BuildTargetIdentifier];
}
```

Response:

- result: `RustWorkspaceResult`, defined as follows

```ts
export interface RustWorkspaceResult {
    
    /** List containing structures descibing packages being in the workspace*/
    packages: List[RustPackage];
    
    /** Maps dependency packageId to raw dependency information (as returned by using 'cargo metadata') */
    rawDependencies: map<String, RustRawDependency>;//TODO check how smithy works with maps
    
    /** Maps source name to information about its dependencies */
    dependencies: map<String, RustDependency>;
}
```
which uses definitions below:
- `RustPackage`:
```ts
export interface RustPackage {

    id: String;

    version: String;

    origin: String;

    edition: String;

    source: String;

    targets: List[RustTarget];

    allTargets: List[RustTarget];

    features: List[RustFeature];

    enabledFeatures: List[String];

    cfgOptions: RustCfgOptions;

    env: map<String, String>;//TODO check how smithy works with maps

    outDirUrl: String;

    procMacroArtifact: RustProcMacroArtifact;
}

export interface RustTarget {
    
    name: String;

    crateRootUrl: String;

    packageRootUrl: String;

    kind: String;

    edition: String;

    doctest: String;

    requiredFeatures: List[String];
}

export interface RustFeature {

    name: String;

    deps: List[String];
}

export interface RustCfgOptions {
    
    keyValueOptions: Map<String, List[String]>; //TODO check how smithy works with maps

    nameOptions: List[String];
}

export interface RustProcMacroArtifact {

    path: String;
}
```
- `RustRawDependency`:
```ts
export interface RustPackage {
    name: String;

    rename: String;

    kind: String;

    target: String;

    optional: Boolean;

    uses_default_features: Boolean;

    features: List[String];
}


```
- `RustDependency`:
```ts
export interface RustDependency {
    
    target: String;
    
    name: String;
    
    depKinds: List[DepKind];

}

export interface DepKind {
    
    kind: String
    
    target: String

}
```


## Rust Workspace Request

Requests information about rust toolchain that project uses

- method: `buildTarget/rustToolchain`
- params: `RustToolchainParams`
```ts
export interface RustToolchainParams {
    
    targets: List[BuildTargetIdentifier];
    
}
```

Response:

- result: `RustToolchainResult`, defined as follows

```ts
export interface RustToolchainResult {
    
    toolchains: List[RustToolchain];
    
}

export interface RustToolchain {
    
    rustStdLib: RustStdLib;
    
    cargoBinPath: String;
    
    procMacroSrvPath: String;

}

export interface RustStdLib {

    rustcSysroot: String;
    
    rustcSrcSysroot: String;
    
    rustcVersion: String;

}
```
