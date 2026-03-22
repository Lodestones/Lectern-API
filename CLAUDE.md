# Lectern-API - Claude Code Instructions

## Overview

Lectern-API is a Maven Java library that defines the API interfaces for Lectern's server-side effect control system.

## WARNING: Cross-Project Sync

Interface changes here must be reflected in:
- **Lectern-Paper** - implements these interfaces
- **Lectern-Fabric** - consumes the network contract defined by these interfaces

See `../CLAUDE.md` for the full cross-project architecture.

## General Conventions

- Do not add documentation files to git. They are Obsidian vault files managed separately.
- Batch commits for confirmed fixes, not after every individual change.
