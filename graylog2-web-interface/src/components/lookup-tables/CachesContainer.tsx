/*
 * Copyright (C) 2020 Graylog, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
import * as React from 'react';

import { useGetAllCaches } from 'hooks/lookup-tables/useLookupTableCachesAPI';
import { Spinner } from 'components/common';

type Props = {
  children: React.ReactChild[],
};

const CachesContainer = ({ children }: Props) => {
  const { caches, pagination, loadingCaches } = useGetAllCaches({ page: 1, perPage: 1000000 });

  console.log('caches:', caches);

  return (
    loadingCaches ? <Spinner /> : (
      <div>
        {React.Children.map(
          children,
          (child: React.ReactElement) => React.cloneElement(
            child,
            { caches, pagination },
          ),
        )}
      </div>
    )
  );
};

export default CachesContainer;
